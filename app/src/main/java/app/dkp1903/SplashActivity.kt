package app.dkp1903.throttleback.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast

class SplashActivity : AppCompatActivity() {

    var permissionString = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.READ_PHONE_STATE,//for incoming calls
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.RECORD_AUDIO)//use the android one for the Manifest as we are looking for Android Permissions
    //permission is a static object of the Manifest class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(!hasPermissions(this@SplashActivity, *permissionString))
        {
           ActivityCompat.requestPermissions(this@SplashActivity, permissionString, 131)
        }
        else
        {//here we have to show the Splash Screen
            // for 1 sec, kill that activity and use an intent to start a new activity
                Handler().postDelayed({
                //first make an intent object
                val startAct = Intent(this@SplashActivity, MainActivity::class.java)
                //Context of application will be taken from SplashActivity and the details will be
                //transferred to the next, that is, the MainActivity
                startActivity(startAct)
                this.finish()
            }, 1000)//the meaning of all this is that the first argument stuff will be done
            //after a time equal to second argument milliseconds
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            131->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED)
                //all permissions granted
                {
                        Handler().postDelayed({
                        //first make an intent object
                        val startAct = Intent(this@SplashActivity, MainActivity::class.java)
                        //Context of application will be taken from SplashActivity and the details will be
                        //transferred to the next, that is, the MainActivity
                        startActivity(startAct)
                        this.finish()
                    }, 1000)
                }
                else{
                    //request code correct but at least one of the permissions not granted
                    Toast.makeText(this@SplashActivity, "Please grant all the permissions", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
                return
            }//when ends here
            else->{
                //toast is a message that is displayed for some time and then disappears
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
                this.finish()//killing the activity(SplashActivity)
                return
            }
        }
    }


    //context means the current state of the application
    // and is used so that new activities and functions can
    // understand what has been going on so far
    fun hasPermissions(context: Context, vararg permissions: String): Boolean
    //vararg will convert the passed array, permissionsString, into a simple array
    {
        var hasAllPermissions = true
        for(permission in permissions)
        {
            val res = context.checkCallingOrSelfPermission(permission)
            if(res != PackageManager.PERMISSION_GRANTED)
            //PackageManager.PERMISSION_GRANTED
            //is the default value provided by Android if a permission has been granted
            {
                hasAllPermissions = false
            }

        }//for ends here
        return hasAllPermissions
    }
}
