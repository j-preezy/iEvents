import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val TAG = "AuthViewModel"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    // Initialize SharedPreferences
    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("auth_pref", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    // Function to perform login
    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                editor.putBoolean("isLoggedIn", true).apply()
                onSuccess.invoke()
            } catch (e: Exception) {
                Log.e(TAG, "Error logging in: ${e.message}")
                onError.invoke(e.message ?: "An unknown error occurred")
            }
        }
    }

    // Function to perform logout
    fun logout() {
        auth.signOut()
        editor.putBoolean("isLoggedIn", false).apply()
    }

    // Function to check if user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false) && auth.currentUser != null
    }
}

@Composable
fun ProvideAuth(viewModel: AuthViewModel, content: @Composable () -> Unit) {
    val authViewModel = remember { viewModel }
    content()
}
