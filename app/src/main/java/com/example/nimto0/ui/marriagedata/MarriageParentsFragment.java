package com.example.nimto0.ui.marriagedata;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.SendData.TempParents;
import com.example.nimto0.models.SendData.TempTestimonial;
import com.example.nimto0.models.marriagedata.Parents;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.FileUtils;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;
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


public class
MarriageParentsFragment extends Fragment implements Validator.ValidationListener, commonListener {


    @NotEmpty
    @Order(1)
    @Length(min=4, max = 20, message="Name must be more than 4 characters.")
    private EditText brideDadET,brideMomET, groomDadET, groomMomET;

    TextInputLayout brideDadTI,brideMomTI, groomDadTI, groomMomTI;
    ImageView brideDadIV,brideMomIV, groomDadIV, groomMomIV;
    String brideDadName,brideMomName, groomDadName, groomMomName;
    SwitchCompat parentSwitch;
    ProgressBar progressBar;
    AppCompatButton updateParentButton;
    Validator validator;
    String brideDadImagePath,brideMomImagePath, groomDadImagePath, groomMomImagePath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_parents, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        brideDadET = view.findViewById(R.id.parents_bride_f_et);
        brideDadTI = view.findViewById(R.id.parents_bride_f_et_);
        brideDadIV = view.findViewById(R.id.parents_bride_f_iv);

        brideMomET = view.findViewById(R.id.parents_bride_m_et);
        brideMomTI = view.findViewById(R.id.parents_bride_m_et_);
        brideMomIV = view.findViewById(R.id.parents_bride_m_iv);

        groomDadET = view.findViewById(R.id.parents_groom_f_et);
        groomDadTI = view.findViewById(R.id.parents_groom_f_et_);
        groomDadIV = view.findViewById(R.id.parents_groom_f_iv);


        groomMomET = view.findViewById(R.id.parents_groom_m_et);
        groomMomTI = view.findViewById(R.id.parents_groom_m_et_);
        groomMomIV = view.findViewById(R.id.parents_groom_m_iv);


        updateParentButton = view.findViewById(R.id.marraige_parent_update);
        parentSwitch  = view.findViewById(R.id.parents_switch_compat_marriage);
        progressBar = view.findViewById(R.id.parents_progressbar_marriage);

        validator = new Validator(this);
        validator.setValidationListener(this);

        inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getParents());
        clickEvents();
        disableAllEditText();




    }

    private void clickEvents() {

        updateParentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brideDadName = brideDadET.getText().toString();
                brideMomName = brideMomET.getText().toString();
                groomDadName = groomDadET.getText().toString();
                groomMomName = groomMomET.getText().toString();

                validator.validate();

            }
        });

        parentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    updateParentButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    updateParentButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });

        brideDadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              imageSelection(Constants.PICK_IMAGE_FROM_GALLARY_ONE);
            }
        });

        brideMomIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelection(Constants.PICK_IMAGE_FROM_GALLARY_TWO);
            }
        });

        groomDadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelection(Constants.PICK_IMAGE_FROM_GALLARY_THREE);
            }
        });

        groomMomIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelection(Constants.PICK_IMAGE_FROM_GALLARY_FOUR);
            }
        });

    }

    private void imageSelection(int pickImageFromGallaryOne) {
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
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImageFromGallaryOne);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void enableAllEditText() {
        brideDadTI.setEnabled(true);
        brideMomTI.setEnabled(true);
        groomDadTI.setEnabled(true);
        groomMomTI.setEnabled(true);

        groomDadIV.setClickable(true);
        groomMomIV.setClickable(true);
        brideDadIV.setClickable(true);
        brideMomIV.setClickable(true);

    }

    private void disableAllEditText() {
        brideDadTI.setEnabled(false);
        brideMomTI.setEnabled(false);
        groomDadTI.setEnabled(false);
        groomMomTI.setEnabled(false);

        groomDadIV.setClickable(false);
        groomMomIV.setClickable(false);
        brideDadIV.setClickable(false);
        brideMomIV.setClickable(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && data != null){
           Uri imageData = data.getData();
           /*
            if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                imagePath = new FileUtils(getActivity()).getPath(imageData);
            }
            */


           switch (requestCode){
               case Constants.PICK_IMAGE_FROM_GALLARY_ONE:
                   brideDadIV.setImageURI(imageData);
                   if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                       brideDadImagePath = new FileUtils(getActivity()).getPath(imageData);
                   }
                   break;
               case Constants.PICK_IMAGE_FROM_GALLARY_TWO:
                   brideMomIV.setImageURI(imageData);
                   if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                       brideMomImagePath = new FileUtils(getActivity()).getPath(imageData);
                   }
                   break;
               case Constants.PICK_IMAGE_FROM_GALLARY_THREE:
                   groomDadIV.setImageURI(imageData);
                   if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                       groomDadImagePath = new FileUtils(getActivity()).getPath(imageData);
                   }
                   break;
               case Constants.PICK_IMAGE_FROM_GALLARY_FOUR:
                   groomMomIV.setImageURI(imageData);
                   if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                       groomMomImagePath = new FileUtils(getActivity()).getPath(imageData);
                   }
                   break;
           }
       }


       // super.onActivityResult(requestCode, resultCode, data);
    }


    private void inflateData(Parents parents) {
        brideDadET.setText(parents.getBride_father_fullname());
        brideMomET.setText(parents.getBride_mother_fullname());
        groomDadET.setText(parents.getGroom_father_fullname());
        groomMomET.setText(parents.getGroom_mother_fullname());

        Glide.with(getActivity())
                .load(Constants.baseURL+parents.getBride_father_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(brideDadIV);

        Glide.with(getActivity())
                .load(Constants.baseURL+parents.getBride_mother_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(brideMomIV);

        Glide.with(getActivity())
                .load(Constants.baseURL+parents.getGroom_father_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(groomDadIV);

        Glide.with(getActivity())
                .load(Constants.baseURL+parents.getGroom_mother_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(groomMomIV);


    }

    private MultipartBody.Part prepareImagePart(String path, String partName){
        // SRC: https://www.youtube.com/watch?v=1VVbyr2Ck_Q
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
  //      RequestBody requestBody = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(Uri.fromFile(file))), file);
        return  MultipartBody.Part.createFormData(partName, file.getName(),requestBody);

    }

    @Override
    public void onValidationSucceeded() {


        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Want to update Information on Parents!")
                .setConfirmText("Just do it!")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();


                    progressBar.setVisibility(View.VISIBLE);
                    updateParentButton.setClickable(false);
                    WebServices.verifyToken(new MainInterface.tokenListener() {
                        @Override
                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                            if(flag){
                                if(returnWithNewRefresh){
                                    Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                }

                                WebServices.updateMarriageParentsData(MarriageParentsFragment.this, "Bearer "+accessToken, ResourceManager.getAllMarriageDataOfselectedOrder().getParents().getMarriage_data(), new TempParents(brideDadName,brideMomName, groomDadName, groomMomName));

                            }else{
                                Utils.goBackToLoginWhenRefreshExpires(getActivity());
                            }

                        }
                    }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());





                })
                .show();




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

    @Override
    public void onCommonResponse(boolean flag) {
        updateParentButton.setClickable(true);

        if (flag){
            inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getParents());
            parentSwitch.setChecked(false);

        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                    Log.e("Maine","Permission granted");
                } else {
                    Log.e("Maine","Permission denied.");
                    Toast.makeText(getContext(), "GET_ACCOUNTS Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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

}

/*
    private boolean validate(String brideDadName, String brideMomName, String groomDadName, String groomMomName) {
        boolean valid = true;
        if (brideDadName.isEmpty()) {
            brideDadET.setError("BrideDad Name field is empty");
            valid = false;
        } else {
            brideDadET.setError(null);
        }

        if (brideMomName.isEmpty()) {
            brideDadET.setError("BrideMom Name field is empty");
            valid = false;
        } else {
            brideMomET.setError(null);
        }

        if (groomDadName.isEmpty()) {
            groomDadET.setError("GroomDad Name field is empty");
            valid = false;
        } else {
            groomDadET.setError(null);
        }

        if (groomMomName.isEmpty()) {
            groomMomET.setError("GroomDad Name field is empty");
            valid = false;
        } else {
            groomMomET.setError(null);
        }

        return valid;
    }


*/