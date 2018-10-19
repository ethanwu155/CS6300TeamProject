package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.sdpvocabquiz.ui.Application;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSubstring;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {
    // Reference: https://android.jlelse.eu/the-basics-of-android-espresso-testing-activities-fragments-7a8bfbc16dc5
    // Reference: https://stackoverflow.com/questions/30191715/start-activity-for-testing
    private static String testUsername1;
    private static String testUsername2;
    private TestUtils tu;

    private Intent makeLoginIntentFor(String user) {
        Intent i = new Intent();
        i.putExtra("currentUser", user);
        return i;
    }

    @Rule
    public ActivityTestRule<Application> mApplicationActivityTestRule =
        new ActivityTestRule<Application>(Application.class, true, false);

    @Before
    public void init() {
        tu = new TestUtils();

        // Generate random usernames for our two test users
        testUsername1 = TestUtils.randAlphanumeric(8);
        testUsername2 = TestUtils.randAlphanumeric(8);

        // And register them in the database if needed
        tu.registerUser(testUsername1);
        tu.registerUser(testUsername2);
    }

    @Test
    public void welcomeMessageContainsCorrectUsername() throws Exception {
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.welcomeTextView)).check(matches(withSubstring(testUsername1)));
        mApplicationActivityTestRule.finishActivity();

        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername2));
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.welcomeTextView)).check(matches(withSubstring(testUsername2)));
    }

    @Test
    public void addQuiz_cancelReturnsToListView() throws Exception {
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.cancelButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void addQuiz_withoutNameAndDescription_fails() throws Exception {
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());

        // No name or description
        onView(withId(R.id.saveButton)).perform(click()).check(matches(isDisplayed()));

        // Name but no description
        onView(withId(R.id.quizNameTextEdit)).perform(replaceText(randomQuizName));
        onView(withId(R.id.saveButton)).perform(click()).check(matches(isDisplayed()));

        // Description but no name
        onView(withId(R.id.quizNameTextEdit)).perform(clearText());
        onView(withId(R.id.quizDescripTextEdit)).perform(replaceText("A Descrip"));
        onView(withId(R.id.saveButton)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void addQuiz_withNoWords_fails() throws Exception {
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.quizNameTextEdit)).perform(replaceText(randomQuizName));
        onView(withId(R.id.quizDescripTextEdit)).perform(replaceText("A Descrip"));
        onView(withId(R.id.saveButton)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void addWord_opensNewWordWindow() throws Exception {
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.newWordLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void addQuiz_withoutEnoughBadDefs_fails() throws Exception {
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.quizNameTextEdit)).perform(replaceText(randomQuizName));
        onView(withId(R.id.quizDescripTextEdit)).perform(replaceText("A Descrip"));

        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.newWord)).perform(replaceText("Word1"));
        onView(withId(R.id.definition)).perform(replaceText("Def1"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.saveButton)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void addQuiz_withProperInputs_succeeds() throws Exception {
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(testUsername1));
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.quizNameTextEdit)).perform(replaceText(randomQuizName));
        onView(withId(R.id.quizDescripTextEdit)).perform(replaceText("A Descrip"));

        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.newWord)).perform(replaceText("Word1"));
        onView(withId(R.id.definition)).perform(replaceText("Def1"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef1"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef2"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef3"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
    }

}
