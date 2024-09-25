import android.content.Context
import android.content.SharedPreferences
import com.intsab.intsabwether.utils.SharedPrefUtils
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SharedPrefUtilsTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setup() {
        // Initialize mocks before each test
        context = mock(Context::class.java)
        sharedPreferences = mock(SharedPreferences::class.java)
        editor = mock(SharedPreferences.Editor::class.java)

        // Define the behavior of mocks
        `when`(context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
    }

    @Test
    fun testSaveCityToPreferences() {
        val cityName = "New York"

        SharedPrefUtils.saveCityToPreferences(context, cityName)

        // Verify that the editor putString method was called with the correct parameters
        verify(editor).putString("city_name", cityName)
        verify(editor).apply()  // Ensure apply was called
    }

    @Test
    fun testGetCityFromPreferences_WhenCityIsSaved() {
        val cityName = "Paris"

        // Mock the behavior of SharedPreferences
        `when`(sharedPreferences.getString("city_name", "Dubai")).thenReturn(cityName)

        val result = SharedPrefUtils.getCityFromPreferences(context)

        assertEquals(cityName, result)  // Verify that the returned value is what we set
    }

    @Test
    fun testGetCityFromPreferences_WhenCityIsNotSaved() {
        // Mock the behavior to return null for the city name
        `when`(sharedPreferences.getString("city_name", "Dubai")).thenReturn(null)

        val result = SharedPrefUtils.getCityFromPreferences(context)

        assertEquals("Dubai", result)  // Default value should be returned
    }
}
