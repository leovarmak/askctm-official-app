package io.github.yhdesai.karthik_webview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;

import com.onesignal.OneSignal;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context = this;


    private WebView webView;


    public void openDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_demo);
        dialog.setTitle(R.string.dialog_title);
        dialog.show();
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            return false;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_gallery);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);     // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        webView = findViewById(R.id.webview);
        webView.loadUrl("file:///android_asset/index.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (isOnline() == false) {
            openDialog();
        } else {   //TODO
            webView.loadUrl(getString(R.string.url) + "home.php");
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {   //TODO cod here
//            Toast.makeText(MainActivity.this, "The menu button was clicked",
//                    Toast.LENGTH_LONG).show();
            if (isOnline() == false) {
                openDialog();
            } else {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "notifications/notifications.html");
                setTitle("Notifications");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (isOnline() == false) {
            openDialog();
        } else {
            if (id == R.id.home) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "home.php");

                setTitle("Home");
            } else if (id == R.id.vision) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "vision-mission.php");

                setTitle("Vision");
            } else if (id == R.id.govern) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "governing-body.php");


                setTitle("Governing Body");
            } else if (id == R.id.courses) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "courses.php");


                setTitle("Courses");
            } else if (id == R.id.facilities) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "facilities.php");

                setTitle("Facilities");
            } else if (id == R.id.gallery) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "gallery.php");

                setTitle("Gallery");
            } else if (id == R.id.faculty) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "faculty.php");

                setTitle("Faculty");
            } else if (id == R.id.admission) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "admission-fee.php");

                setTitle("Admission & Fee");
            } else if (id == R.id.placements) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "placements.php");

                setTitle("Placements");
            } else if (id == R.id.contact) {

                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.startsWith("tel:")) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                            startActivity(intent);
                            return true;
                        }
                        if (url.startsWith("mailto:")) {
                            Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                            startActivity(i);
                            return true;
                        } else {
                            view.loadUrl(url);
                        }
                        return false;
                    }
                }); //going to dinner, will be back soon

                webView.loadUrl(getString(R.string.url) + "contact-us.php");
                setTitle("Contact Us");

            } else if (id == R.id.developers) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "developers.php");

                setTitle("Developers");
            } else if (id == R.id.syllabus) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "syllabus_branch.php");

                setTitle("Syllabus");
            } else if (id == R.id.timetable) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "timetable_branch.php");

                setTitle("Timetables");
            } else if (id == R.id.notes) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "lecture_branch.php");

                setTitle("Lecture Notes");
            } else if (id == R.id.marks) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "internal.php");

                setTitle("Internal Marks");
            } else if (id == R.id.qp) {

                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(getString(R.string.url) + "qp_branch.php");

                setTitle("Previous Question Papers");
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openSettings(View view) {
        Intent openSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(openSettingsIntent);
    }
}