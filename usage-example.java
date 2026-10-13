 private PermissionHelper permissionHelper;

   permissionHelper = new PermissionHelper(this);

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissionHelper.check(Manifest.permission.READ_MEDIA_IMAGES, status -> {
                if (status == 1) {
                    open_files();
                } else {
                    Toast.makeText(this, "Нет доступа к фотографиям!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            permissionHelper.check(Manifest.permission.READ_EXTERNAL_STORAGE, status -> {
                if (status == 1) {
                    open_files();
                } else {
                    Toast.makeText(this, "Нет доступа к фотографиям!", Toast.LENGTH_SHORT).show();
                }
            });
        }