package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.sdpvocabquiz.ui.Application;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSubstring;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class QuizAddDeleteTests {
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

    // The remaining tests may require a recently cleared database, or the added quizes may be off-screen
    // TODO: Any way to get around this?^

    @Test
    public void addQuiz_backdoorQuizAddUtilityWorks() throws Exception {
        // Make a username and register it
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        // Add a Quiz
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        // Login w/ user
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));

        // See if it shows up in the GUI
        onView(withText(randomQuizName)).check(matches(isDisplayed()));
//        RecyclerView recyclerView = mApplicationActivityTestRule.getActivity().findViewById(R.id.recyclerView);
    }

    @Test
    public void delQuiz_byOtherUser_fails() throws Exception {
        // Register a username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        // Add a Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        // Register another username
        String anotherUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(anotherUserName);

        // Verify 2nd user has no quizzes to delete
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(anotherUserName));
        onView(withId(R.id.menu_delete)).perform(click());
        onView(withId(R.id.welcomeTextView)).check(matches(isDisplayed()));
        // TODO: Might need better failure check here
    }

    @Test
    public void delQuiz_byCurrentUser_succeeds() throws Exception {
        // Register a username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        // Add a Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        // Login w/ user and try to delete
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));
        onView(withId(R.id.menu_delete)).perform(click());
        onView(withText(randomQuizName)).check(matches(isDisplayed())).perform(click());
        // TODO: Add a quizExists(name) method to TestUtils
    }

    //Test for checking next Question functionality
    @Test
    public void practiceMultpleQuestionQuiz() throws Exception {
        //Register username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        //Add Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);

        //Login with user and check disabled confirm button
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));

        //add quiz contents
        onView(withId(R.id.menu_add)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.quizNameTextEdit)).perform(replaceText(randomQuizName));
        onView(withId(R.id.quizDescripTextEdit)).perform(replaceText("A Descrip"));

        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.newWord)).perform(replaceText("Word1"));
        onView(withId(R.id.definition)).perform(replaceText("Def1"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addWordButton)).perform(click());
        onView(withId(R.id.newWord)).perform(replaceText("Word2"));
        onView(withId(R.id.definition)).perform(replaceText("Def2"));
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

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef4"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef5"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.addBadDefButton)).perform(click());
        onView(withId(R.id.definition)).perform(replaceText("BadDef6"));
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.saveButton)).perform(click());

        //testing for next question functionality
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.card_view_answer_2)).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());
        onView(withText("OKAY")).perform(click());
        onView(withId(R.id.card_view_answer_1)).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());
        onView(withText("OKAY")).perform(click());
        Log.d("PracticeQuizTests-Info" ,"Quiz Completed and Logged in DB");
    }

    //Test for checking disabled confirm button without answer
    @Test
    public void practiceQuizDisableConfirm() throws Exception {
        //Register username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        //Add Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        //Login with user and check disabled confirm button
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(not(isEnabled())));
    }

    //Test for Practicing Quiz
    @Test
    public void practiceQuiz_bycurrentUser() throws Exception {
        //Register username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        //Add Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        //Login with user and practice quiz
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.card_view_answer_2)).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());
        onView(withText("OKAY")).perform(click());
        Log.d("PracticeQuizTests-Info" ,"Quiz Completed and Logged in DB");
    }

    //Test for quiz status
    @Test
    public void quizStatus() throws Exception {
        //Register username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        //Add Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        //Login with user and practice quiz
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.card_view_answer_2)).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());

        //not the best way to test this (Dialog box with students current quiz score)
        onView(withText("OKAY")).check(matches(isDisplayed()));
    }

    //Test for verifying word is correct
    @Test
    public void verifyWordPractice() throws Exception {
        //Register username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        //Add Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        //Login with user and practice quiz
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(randomUserName));
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.word)).check(matches(isDisplayed()));
    }

    //Take another user's quiz
    @Test
    public void practiceAnotherUserQuiz() throws Exception {
        // Register a username
        String randomUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(randomUserName);

        // Add a Quiz by this user
        String randomQuizName = "Quiz-"+TestUtils.randAlphanumeric(8);
        tu.addBaselineQuiz(randomUserName, randomQuizName);

        // Register another username
        String anotherUserName = TestUtils.randAlphanumeric(12);
        tu.registerUser(anotherUserName);

        //Login as anotherUserName and take quiz from randomUser
        mApplicationActivityTestRule.launchActivity(makeLoginIntentFor(anotherUserName));
        onView(withText(randomQuizName)).perform(click());
        onView(withText("Practice Quiz")).perform(click());
        onView(withId(R.id.card_view_answer_2)).perform(click());
        onView(withId(R.id.confirmButton)).check(matches(isEnabled())).perform(closeSoftKeyboard(), click());
        onView(withText("OKAY")).perform(click());
        Log.d("PracticeQuizTests-Info" ,"Quiz Completed and Logged in DB");
    }
}
