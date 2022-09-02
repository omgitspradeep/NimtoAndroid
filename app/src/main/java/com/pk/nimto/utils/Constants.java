package com.pk.nimto.utils;

public class Constants {
    public static final String ADD_NEW_IMAGE = "Add New Image" ;
    public static final String UPDATE_IMAGE = "Update Image" ;
    public static int currentLoggedInUser=1;

    private static final String TAG = "ANON";

    //CODE FOR GETTING IMAGE FROM GALLERY
    public static final int PICK_IMAGE_FROM_GALLARY_ONE = 1;
    public static final int PICK_IMAGE_FROM_GALLARY_TWO = 2;
    public static final int PICK_IMAGE_FROM_GALLARY_THREE = 3;
    public static final int PICK_IMAGE_FROM_GALLARY_FOUR = 4;
    public static final int PICK_IMAGE = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    //APIS
    public static final String baseURL = "https://muktinath.herokuapp.com";
    public static final String baseURLImage = "https://mylibraryclub.herokuapp.com";
    public static final String tempBaseUrl = "https://jsonplaceholder.typicode.com";

    // WEBVIEW
    public static final String viewAsApi = baseURL+"/api/orders/viewAsApi/";  //https://muktinath.herokuapp.com/api/orders/viewAsApi/<order_id>/
    public static final String dashboardApi = baseURL+"/api/dashboard/";  //https://muktinath.herokuapp.com/api/dashboard/<order_id>/
    public static final String WEBVIEW_TITLE = "webviewTitle";
    public static final String URL_LINK = "urlLink";


    public static final String IS_USER_LOGGED_IN = "userLoggedIn";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String LAST_USERID = "LAST_USERID";
    public static final String LAST_READER_PASS = "LAST_READER_PASS";


    // Intent variables
    public static final String ORDER_ID_OF_INVITEES = "ORDER_ID_OF_INVITEES";
    public static final String INTENT_FROM = "intent_from";
    public static final String ORDER_ID = "order_id";
    public static final String THEME_ID = "theme_id";
    public static final String TASK = "task";
    public static final String UPDATE = "Update";
    public static final String CREATE = "Create";
    public static final String VIEW = "View";
    public static final String DATA = "data";

    //Data inflation constants
    public static final String Mr = "Mr. ";
    public static final String Ms = "Ms. ";
    public static final String WEDDING = "Wedding";
    public static final String RECEPTION = "Reception";
    public static final String MARRIAGE = "Marriage";
    public static final String BIRTHDAY = "Birthday";
    public static final String OPENING = "Opening";

    // Fragment Title
    public static final String MAIN_SCREEN = "Invitation Information";
    public static final String CONTACT_SCREEN = "Contact Info";
    public static final String MEETINGPOINT_SCREEN = "Meeting Location ";
    public static final String GALLERY_SCREEN = "Gallery Images ";
    public static final String PARENTS_SCREEN = "Parents Info ";
    public static final String TESTIMONIALS_SCREEN = "Testimonial Info ";





}


/*    Dialog



1. Spinner
                        SweetAlertDialog pDialog =ResourceManager.sweetAlertDialog(BookDetailActivity.this);
                        pDialog.show();

2. Dialog Warning

                        new SweetAlertDialog(BookDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Are you sure?")
                                        .setContentText("Won't be able to recover this book!")
                                        .setConfirmText("Yes,delete it!")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                // Do your action here
                                        })
                                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show());

 3.  Single button (NORMAL TYPE)

 new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Want to update this book!")
                    .setConfirmText("Just do it!")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        sweetAlertDialog.dismiss();

                        SweetAlertDialog pDialog =ResourceManager.sweetAlertDialog(BookUploadActivity.this);
                        pDialog.show();


                    })
                    .show();



 */


/*
Handler

 new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Your Code
                                    pDialog.cancel();
                                }
                            }, 3000);




 */



/*
    public static final String deletBook= "/bbc/api/getBooks/";   // https://mylibraryclub.herokuapp.com/bbc/api/getBooks/<int:userID>
    public static final String getBooks= "/bbc/api/allbooks/";    // https://mylibraryclub.herokuapp.com/bbc/api/allbooks/
    public static final String InviteeAPI="sp/api/getMyGuests/";  // https://happycouples.herokuapp.com/sp/api/getMyGuests/

*/

/*
ANIMATIONS
1. It is the white and purple particles originationg from place of touch and over outward
       new ParticleSystem(getActivity(), 100, R.drawable.animated_confetti, 5000)
                        .setSpeedRange(0.1f, 0.25f)
                        .setRotationSpeedRange(90, 180)
                        .setInitialRotationRange(0, 360)
                        .oneShot(v, 100);

2.  confeti2 pic from username view and confeti3 from password view comes for 8/s for 10 seconds. They rotates and goes down

                new ParticleSystem(getActivity(), 80, R.drawable.confeti2, 1000)
                        .setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
                        .setRotationSpeed(144)
                        .setAcceleration(0.000017f, 90)
                        .emit(root.findViewById(R.id.username), 8);

                new ParticleSystem(getActivity(), 80, R.drawable.confeti3, 1000)
                        .setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
                        .setRotationSpeed(144)
                        .setAcceleration(0.000017f, 90)
                        .emit(root.findViewById(R.id.password), 8);

3. It raises the green smoke from view login which expands and move up little and then disappears

                new ParticleSystem(getActivity(), 4, R.drawable.dust, 3000)
                        .setSpeedByComponentsRange(-0.025f, 0.025f, -0.06f, -0.08f)
                        .setAcceleration(0.00001f, 30)
                        .setInitialRotationRange(0, 360)
                        .addModifier(new AlphaModifier(255, 0, 1000, 3000))
                        .addModifier(new ScaleModifier(0.5f, 2f, 0, 1000))
                        .oneShot(root.findViewById(R.id.login), 4);

4.  Pink star comes from view "v" continously just like fire from dragon. (Emitter)

                ParticleSystem ps = new ParticleSystem(getActivity(), 100, R.drawable.star_pink, 1000);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
                ps.setRotationSpeedRange(90, 180);
                ps.setAcceleration(0.00013f, 90);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit(v, 100);

5. Same as above but with time limit (Emitter time limited)

                ParticleSystem ps = new ParticleSystem(getActivity(), 100, R.drawable.star_pink, 1000);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
                ps.setRotationSpeedRange(90, 180);
                ps.setAcceleration(0.00013f, 90);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.emit(v, 100, 2000);

 6. Emits pink star from view v which falls like waterfall continously. (GRAVITY)

                new ParticleSystem(getActivity(), 100, R.drawable.star_pink, 3000)
                        .setAcceleration(0.00013f, 90)
                        .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                        .setFadeOut(200, new AccelerateInterpolator())
                        .emitWithGravity(v, Gravity.BOTTOM, 30);

7. It originates from view v to outside like a blast of firework in sky. It perform one time action (FIREWORK)

                ParticleSystem ps = new ParticleSystem(getActivity(), 100, R.drawable.star_pink, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.1f, 0.25f);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.oneShot(v, 70);

                ParticleSystem ps2 = new ParticleSystem(getActivity(), 100, R.drawable.star_white, 800);
                ps2.setScaleRange(0.7f, 1.3f);
                ps2.setSpeedRange(0.1f, 0.25f);
                ps.setRotationSpeedRange(90, 180);
                ps2.setFadeOut(200, new AccelerateInterpolator());
                ps2.oneShot(v, 70);

8. Launches drawable originating from view v
                // Launch 2 particle systems one for each image
                ParticleSystem ps = new ParticleSystem(getActivity(), 100, R.drawable.animated_star_white_border, 800);
                ps.setScaleRange(0.7f, 1.3f);
                ps.setSpeedRange(0.1f, 0.25f);
                ps.setAcceleration(0.0001f, 90);
                ps.setRotationSpeedRange(90, 180);
                ps.setFadeOut(200, new AccelerateInterpolator());
                ps.oneShot(v, 100);

9.  FIres drawables out from view v, they grows and starts to fade beautifully.

                new ParticleSystem(getActivity(), 10, R.drawable.star, 3000)
                        .setSpeedByComponentsRange(-0.1f, 0.1f, -0.1f, 0.02f)
                        .setAcceleration(0.000003f, 90)
                        .setInitialRotationRange(0, 360)
                        .setRotationSpeed(120)
                        .setFadeOut(2000)
                        .addModifier(new ScaleModifier(0f, 1.5f, 0, 1500))
                        .oneShot(v, 10);

 */


/*

Material Spinner


<com.google.android.material.textfield.TextInputLayout
            android:id="@+id/upload_mybook_language_"
            style="@style/Widget.MaterialComponents.TextInputLayout.FilledBox.ExposedDropdownMenu"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Language"
            app:layout_constraintTop_toBottomOf="@+id/upload_mybook_desc_"
            android:orientation="horizontal"
            android:layout_marginTop="10dp"
            app:startIconDrawable="@drawable/ic_baseline_language_24"
            >

            <AutoCompleteTextView
                android:id="@+id/upload_mybook_language"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="none"
                />

        </com.google.android.material.textfield.TextInputLayout>


        AutoCompleteTextView bookStatusSpinner =findViewById(R.id.upload_mybook_language)
        ArrayAdapter<CharSequence> languageSelectionAdapter = ArrayAdapter.createFromResource(this,R.array.book_language_array, R.layout.dropdown_menu_popup_item);
        languageSelectionSpinner.setAdapter(languageSelectionAdapter);



 */