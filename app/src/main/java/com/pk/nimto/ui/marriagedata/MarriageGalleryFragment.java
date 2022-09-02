package com.pk.nimto.ui.marriagedata;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pk.nimto.R;
import com.pk.nimto.adapters.CustomizedGalleryAdapter;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.models.SendData.TempGallery;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.FileUtils;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class MarriageGalleryFragment extends Fragment implements Validator.ValidationListener, MainInterface.onGalleryImageUpdate, MainInterface.onGalleryImageCreate{


    Gallery simpleGallery;
    CustomizedGalleryAdapter customGalleryAdapter;
    List<com.pk.nimto.models.marriagedata.Gallery> galleryList;
    LinearLayout imageStage;
    FloatingActionButton createImage;
    com.pk.nimto.models.marriagedata.Gallery selectedGalleryImageObject;
    boolean isGalleryItemClicked =false;

    @NotEmpty
    @Order(1)
    @Length(min=3, max = 25, message="Please make title short and simple.(3-25 characters)")
    EditText imageTitleET;

    @NotEmpty
    @Order(2)
    @Length(min=10, max = 150, message="Please describe the image in brief ( atleast 10 characters).")
    EditText imageDetailET;
    ImageButton deleteImage;
    ImageView selectedImageIV;
    TextInputLayout imageTitleTI, imageDetailTI;
    String imageTitleText, imageDetailText;
    SwitchCompat gallerySwitch;
    ProgressBar progressBar;
    AppCompatButton updateGalleryImageButton;
    Validator validator;

    String selectedImagePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        galleryList = ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries();
        //Initialization
        simpleGallery = view.findViewById(R.id.all_images_gallery);
        imageStage = view.findViewById(R.id.image_display_stage_or_layout);
        imageTitleET = view.findViewById(R.id.marraige_gallery_title_et);
        imageDetailET = view.findViewById(R.id.marraige_gallery_detail_et);
        imageTitleTI = view.findViewById(R.id.marraige_gallery_title_et_);
        imageDetailTI = view.findViewById(R.id.marraige_gallery_detail_et_);
        selectedImageIV = view.findViewById(R.id.imageView);
        deleteImage = view.findViewById(R.id.gallery_ib_marriage_delete);


        createImage = view.findViewById(R.id.create_image_gallery_fab);
        updateGalleryImageButton = view.findViewById(R.id.marraige_gallery_update);
        gallerySwitch  = view.findViewById(R.id.gallery_switch_compat_marriage);
        progressBar = view.findViewById(R.id.gallery_progressbar_marriage);

        validator = new Validator(this);
        validator.setValidationListener(this);

        inflateData(galleryList);

        disableAllEditText();
        clickEvents();

    }

    private void inflateData(List<com.pk.nimto.models.marriagedata.Gallery> galleries) {
        // initialize the adapter
        customGalleryAdapter = new CustomizedGalleryAdapter(getActivity(), galleryList);
        // set the adapter for gallery
        simpleGallery.setAdapter(customGalleryAdapter);

    }

    private void clickEvents() {

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Want to remove this Image.")
                        .setConfirmText("Just do it!")
                        .setConfirmClickListener(sweetAlertDialog -> {
                            sweetAlertDialog.dismiss();
                            deleteImage.setClickable(false);
                            progressBar.setVisibility(View.VISIBLE);

                            WebServices.verifyToken(new MainInterface.tokenListener() {
                                @Override
                                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                    if(flag){
                                        if(returnWithNewRefresh){
                                            Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                        }
                                        WebServices.deleteMarriageGalleryData(MarriageGalleryFragment.this,
                                                selectedGalleryImageObject.getId() ,
                                                true,
                                                "Bearer "+accessToken);
                                    }else{
                                        Utils.goBackToLoginWhenRefreshExpires(getActivity());
                                    }

                                }
                            }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());



                        })
                        .show();
            }
        });

        // Let us do item click of gallery and image can be identified by its position
        simpleGallery.setOnItemClickListener((parent, view2, position, id) -> {
            // Whichever image is clicked, that is set in the  selectedImageView
            // position will indicate the location of image

            selectedGalleryImageObject= galleryList.get(position);
            whenImageIsClicked();
            deleteImage.setVisibility(View.VISIBLE);
            gallerySwitch.setVisibility(View.VISIBLE);
            updateGalleryImageButton.setVisibility(View.GONE);


        });

        gallerySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    deleteImage.setVisibility(View.INVISIBLE);
                    updateGalleryImageButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    deleteImage.setVisibility(View.VISIBLE);
                    updateGalleryImageButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });

        selectedImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelection(Constants.PICK_IMAGE);
            }
        });

        updateGalleryImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageTitleText = imageTitleET.getText().toString();
                imageDetailText = imageDetailET.getText().toString();

                validator.validate();

            }
        });

        createImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGalleryItemClicked = false;
                imageStage.setVisibility(View.VISIBLE);
                gallerySwitch.setChecked(true);
                imageTitleET.setText("");
                imageDetailET.setText("");
                selectedImageIV.setImageDrawable(null);
                updateGalleryImageButton.setText(Constants.ADD_NEW_IMAGE);
                deleteImage.setVisibility(View.INVISIBLE);
                gallerySwitch.setVisibility(View.INVISIBLE);

            }
        });

    }

    private void imageSelection(int pickImageFromGallary) {
        final CharSequence[] options = {"Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Upload Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImageFromGallary);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == Constants.PICK_IMAGE && resultCode == RESULT_OK && data != null){
            Uri imageData = data.getData();
            selectedImageIV.setImageURI(imageData);
            if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                selectedImagePath = new FileUtils(getActivity()).getPath(imageData);
            }

        }


        // super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context, Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context, new String[] { permission }, Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    private void whenImageIsClicked() {
        isGalleryItemClicked = true;
        imageStage.setVisibility(View.VISIBLE);
        gallerySwitch.setChecked(false);

        imageTitleET.setText(selectedGalleryImageObject.getTitle());
        imageDetailET.setText(selectedGalleryImageObject.getDetail());
        updateGalleryImageButton.setText(Constants.UPDATE_IMAGE);

        Glide.with(getActivity())
                .load(Constants.baseURL+selectedGalleryImageObject.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(selectedImageIV);
    }

    @Override
    public void onValidationSucceeded() {

        if(isGalleryItemClicked){

            new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Want to update this Image Information!")
                    .setConfirmText("Just do it!")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();

                        progressBar.setVisibility(View.VISIBLE);
                        deleteImage.setClickable(false);
                        updateGalleryImageButton.setClickable(false);
                        WebServices.verifyToken(new MainInterface.tokenListener() {
                            @Override
                            public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                if(flag){
                                    if(returnWithNewRefresh){
                                        Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                    }
                                    WebServices.updateMarriageGalleryData(MarriageGalleryFragment.this,selectedGalleryImageObject.getId() ,false,"Bearer "+accessToken, new TempGallery(imageTitleText, imageDetailText));
                                }else{
                                    Utils.goBackToLoginWhenRefreshExpires(getActivity());
                                }

                            }
                        }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());







                    })
                    .show();


        }else{
            // Add new photo in gallery

            new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Want to upload new Image in Gallery!")
                    .setConfirmText("Just do it!")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();

                        deleteImage.setClickable(false);
                        progressBar.setVisibility(View.VISIBLE);
                        updateGalleryImageButton.setClickable(false);
                        WebServices.verifyToken(new MainInterface.tokenListener() {
                            @Override
                            public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                if(flag){
                                    if(returnWithNewRefresh){
                                        Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                    }
                                    WebServices.createMarriageGalleryData(MarriageGalleryFragment.this,
                                            "Bearer "+accessToken,
                                            imageTitleText,
                                            imageDetailText,
                                            getDataID(),
                                            prepareImagePart(selectedImagePath,"image")
                                    );
                                }else{
                                    Utils.goBackToLoginWhenRefreshExpires(getActivity());
                                }

                            }
                        }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());


                    })
                    .show();


        }


    }

    private MultipartBody.Part prepareImagePart(String path, String partName){
        // SRC: https://www.youtube.com/watch?v=1VVbyr2Ck_Q
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        //      RequestBody requestBody = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(Uri.fromFile(file))), file);
        return  MultipartBody.Part.createFormData(partName, file.getName(),requestBody);

    }

    private int getDataID() {
        return ResourceManager.getAllMarriageDataOfselectedOrder().getMain_data().getMarriage_order();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Log.e("ANON",message);
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void enableAllEditText() {
        selectedImageIV.setClickable(true);
        imageTitleTI.setEnabled(true);
        imageDetailTI.setEnabled(true);
    }

    private void disableAllEditText() {
        selectedImageIV.setClickable(false);
        imageTitleTI.setEnabled(false);
        imageDetailTI.setEnabled(false);
    }


    @Override
    public void isImageCreated(boolean isSuccessful, com.pk.nimto.models.marriagedata.Gallery createdImage) {

        deleteImage.setClickable(true);
        updateGalleryImageButton.setClickable(true); // When button is set visible it must be clickable. Hence it is set true.
        gallerySwitch.setChecked(false);
        if (isSuccessful){
            selectedGalleryImageObject = createdImage;
            galleryList= ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries();
            inflateData(galleryList);
            whenImageIsClicked();
            gallerySwitch.setVisibility(View.VISIBLE);

        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void isImageUpdated(boolean isSuccessful, com.pk.nimto.models.marriagedata.Gallery updatedImage, int imageID, boolean deleteOrUpdate) {

        updateGalleryImageButton.setClickable(true);
        deleteImage.setClickable(true);
        if (isSuccessful){


            if(deleteOrUpdate){
                //For Deletion
                for(int i=0;i<=galleryList.size();i++){
                    if (imageID== galleryList.get(i).getId()){
                        ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries().remove(galleryList.get(i));
                        galleryList=ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries();
                        break;
                    }
                }

                imageStage.setVisibility(View.INVISIBLE);
                deleteImage.setVisibility(View.INVISIBLE);
                deleteImage.setClickable(true);
                updateGalleryImageButton.setVisibility(View.INVISIBLE);
                gallerySwitch.setVisibility(View.INVISIBLE);
                gallerySwitch.setChecked(false);
                inflateData(galleryList);
            }else{

                // For Updation
                selectedGalleryImageObject = updatedImage;
                for(int i=0;i<=galleryList.size();i++){
                    if (imageID== galleryList.get(i).getId()){
                        ResourceManager.getAllMarriageDataOfselectedOrder().getGalleries().set(i,updatedImage);
                        galleryList.set(i, updatedImage);
                        break;
                    }
                }
                inflateData(galleryList);
                whenImageIsClicked();
            }





        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);

    }



}
