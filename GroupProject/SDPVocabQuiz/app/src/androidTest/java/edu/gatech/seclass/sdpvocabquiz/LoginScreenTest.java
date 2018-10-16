package edu.gatech.seclass.sdpvocabquiz;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.sdpvocabquiz.ui.LoginActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void clickRegisterButton_opensRegisterUi() throws Exception {
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.emailTextInput)).check(matches(isDisplayed()));
    }

    @Test
    public void registerNewUser_opensMainMenu() throws Exception {
        onView(withId(R.id.registerButton)).perform(click());

        onView(withId(R.id.userTextEdit)).perform(click(), replaceText("gburdell"));
        onView(withId(R.id.majorTextEdit)).perform(closeSoftKeyboard(), click(), typeText("Computer Science"));
        onView(withId(R.id.emailTextEdit)).perform(closeSoftKeyboard(), click(), typeText("gburdell@gatech.edu"));

        onView(withId(R.id.senioritySpinner)).perform(closeSoftKeyboard(), click());
        onData(allOf(is(instanceOf(String.class)), is("Senior"))).perform(click());
        onView(withId(R.id.senioritySpinner)).check(matches(withSpinnerText(containsString("Senior"))));

        onView(withId(R.id.registerButton)).perform(click());
        // TODO: Alright, now how to check that we're at the main page?
    }

    @Test
    public void loginNonregisteredUser_fails() throws Exception {
        onView(withId(R.id.textInputEdit)).perform(replaceText("abcdefgh"));
        onView(withId(R.id.loginButton)).perform(click()).check(matches(isDisplayed()));
        // Check that the register button is still displayed (i.e. we haven't left the login screen)
        // TODO: Surely there's a better way to check that the login failed?
    }

    @Test
    public void loginRegisteredUser_succeeds() throws Exception {
        onView(withId(R.id.textInputEdit)).perform(replaceText("gburdell"));
        onView(withId(R.id.loginButton)).perform(click());
        // TODO: Again, check here that we've made it to the main page?
    }

    @Test
    public void registerWithoutAllFieldsPopulated_fails() throws Exception {
        // Attempting to register without completing all registration fields shouldn't let us progress
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.userTextEdit)).perform(click(), replaceText("zyxwvu"));
        onView(withId(R.id.registerButton)).perform(closeSoftKeyboard(), click());
        // TODO: Better way to check for failure - look for error popup/tooltip?
        // Maybe when "Register" clicked w/ empty fields, populate those fields with warning messages?
        onView(withId(R.id.registerButton)).check(matches(isDisplayed()));
    }
}
