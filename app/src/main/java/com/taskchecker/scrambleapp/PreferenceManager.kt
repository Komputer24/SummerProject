import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taskchecker2.scrambleapp.Task

object PreferenceManager {
    private const val PREFERENCES_NAME = "task_preferences"
    private const val KEY_TASKS = "tasks_key"
    private val gson = Gson()

    fun saveTasks(context: Context, tasks: List<Task>) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val tasksJson = gson.toJson(tasks)
        with(sharedPreferences.edit()) {
            putString(KEY_TASKS, tasksJson)
            apply()
        }
    }

    fun getTasks(context: Context): List<Task> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val tasksJson = sharedPreferences.getString(KEY_TASKS, null)
        return if (tasksJson != null) {
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(tasksJson, type)
        } else {
            emptyList()
        }
    }
}
