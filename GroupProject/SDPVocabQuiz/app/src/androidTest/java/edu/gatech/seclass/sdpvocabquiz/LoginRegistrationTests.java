package edu.gatech.seclass.sdpvocabquiz;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import edu.gatech.seclass.sdpvocabquiz.ui.LoginActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class LoginRegistrationTests {
    private TestUtils tu;

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void init() {
        // TODO: Clear database here to start tests fresh?
        tu = new TestUtils();
    }

    @Test
    public void loginUnregisteredUser_fails() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);
        onView(withId(R.id.textInputEdit)).perform(replaceText(randomUsername));
        onView(withId(R.id.loginButton)).perform(click()).check(matches(isDisplayed()));
        // TODO: Instead of checking that loginButton is still displayed, check current activity is still Login?
    }

    @Test
    public void clickRegisterButton_opensRegisterUi() throws Exception {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailTextInput)).check(matches(isDisplayed()));
    }

    @Test
    public void registerWithoutAllFields_fails() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);
        onView(withId(R.id.registerButton)).perform(click());

        // All fields empty
        onView(withId(R.id.registerButton)).perform(click()).check(matches(isDisplayed()));

        // Only username
        onView(withId(R.id.userTextEdit)).perform(click(), replaceText(randomUsername));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        // Only major
        onView(withId(R.id.userTextEdit)).perform(clearText());
        onView(withId(R.id.majorTextEdit)).perform(replaceText("Test Major"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        // Only email
        onView(withId(R.id.majorTextEdit)).perform(clearText());
        onView(withId(R.id.emailTextEdit)).perform(replaceText("test1@example.com"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        // Only major & email
        onView(withId(R.id.majorTextEdit)).perform(replaceText("Test Major"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        // Only username & major
        onView(withId(R.id.emailTextEdit)).perform(clearText());
        onView(withId(R.id.userTextEdit)).perform(replaceText(randomUsername));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        // Only username & email
        onView(withId(R.id.majorTextEdit)).perform(clearText());
        onView(withId(R.id.emailTextEdit)).perform(replaceText("test1@example.com"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));
    }

    @Test
    public void registerWithBadEmail_fails() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.userTextEdit)).perform(replaceText(randomUsername));
        onView(withId(R.id.majorTextEdit)).perform(replaceText("Test Major"));

        onView(withId(R.id.emailTextEdit)).perform(replaceText("example"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        onView(withId(R.id.emailTextEdit)).perform(replaceText("example.com"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));

        onView(withId(R.id.emailTextEdit)).perform(replaceText("test2@example"));
        onView(withId(R.id.registerButton)).check(matches(not(isEnabled())));
    }

    @Test
    public void registerWithGoodData_succeeds() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);
        onView(withId(R.id.registerButton)).perform(click());
        // too much monotony - user typeText() here instead (note: fails if the keyboard covers the text box)
        onView(withId(R.id.userTextEdit)).perform(closeSoftKeyboard(), typeText(randomUsername));
        onView(withId(R.id.majorTextEdit)).perform(closeSoftKeyboard(), typeText("Test Major"));
        onView(withId(R.id.emailTextEdit)).perform(closeSoftKeyboard(), typeText(randomUsername+"@gatech.edu"));
        onView(withId(R.id.registerButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
        Log.d("LoginRegistrationTests-Info", "Registered user: \""+randomUsername+"\"");
    }

    @Test
    public void loginRegisteredUser_succeeds() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);

        // register this user in the database
        tu.registerUser(randomUsername);

        // then try to login
        onView(withId(R.id.textInputEdit)).perform(replaceText(randomUsername));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void registerWithTakenUsername_fails() throws Exception {
        String randomUsername = TestUtils.randAlphanumeric(12);

        // register this user in the database
        tu.registerUser(randomUsername);

        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.userTextEdit)).perform(replaceText(randomUsername));
        onView(withId(R.id.majorTextEdit)).perform(replaceText("Test Major"));
        onView(withId(R.id.emailTextEdit)).perform(replaceText(randomUsername+"@gatech.edu"));
        onView(withId(R.id.registerButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click()).check((matches(isDisplayed())));
    }
}
