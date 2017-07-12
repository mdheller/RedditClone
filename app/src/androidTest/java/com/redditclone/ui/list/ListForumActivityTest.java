package com.redditclone.ui.list;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.redditclone.ElapsedTimeIdlingResource;
import com.redditclone.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListForumActivityTest {

    private IdlingResource idlingResource;
    private String textTitle;
    private String textDescription;


    @Rule
    public ActivityTestRule<ListForumActivity> mActivityTestRule = new ActivityTestRule<>(ListForumActivity.class);

    @Test
    public void listForumActivityTest() {

        textTitle = "Net Neutrality explained and why it matters";
        textDescription = "We believe all traffic on the web should be treated equally, Stack Overflow will join some of your other favorite sites from around the web including Netflix, GitHub, Reddit, and Amazon.";

        waitFor(5000); // wait for UI to be properly loaded

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        withParent(allOf(withId(R.id.main_layout),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.title), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.title), isDisplayed()));
        appCompatEditText2.perform(replaceText(textTitle), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.description), isDisplayed()));
        appCompatEditText3.perform(replaceText(textDescription), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.submit_button), withText("Create Post"), isDisplayed()));
        appCompatButton.perform(click());

        waitFor(5000); // wait for UI to be properly loaded

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.upvote_button),
                        withParent(allOf(withId(R.id.tag_list),
                                withParent(withId(R.id.forum_list)))),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.upvote_button),
                        withParent(allOf(withId(R.id.tag_list),
                                withParent(withId(R.id.forum_list)))),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction appCompatImageView3 = onView(
                allOf(withId(R.id.upvote_button),
                        withParent(allOf(withId(R.id.tag_list),
                                withParent(withId(R.id.forum_list)))),
                        isDisplayed()));
        appCompatImageView3.perform(click());

        ViewInteraction appCompatImageView4 = onView(
                allOf(withId(R.id.downvote_button),
                        withParent(allOf(withId(R.id.tag_list),
                                withParent(withId(R.id.forum_list)))),
                        isDisplayed()));
        appCompatImageView4.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.forum_list_recyclerview), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        waitFor(5000); // wait for UI to be properly loaded

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText(textTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_layout),
                                        1),
                                1),
                        isDisplayed()));
        textView.check(matches(withText(textTitle)));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.description), withText(textDescription),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_layout),
                                        1),
                                4),
                        isDisplayed()));
        textView2.check(matches(withText(textDescription)));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.upvote_text), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.tag_list),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("3")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.downvote_text), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.tag_list),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                2)),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText("1")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


    public void waitFor(long waitingTime) {
        //SystemClock.sleep(waitingTime);  // This is replaced with IdlingResource.
        // Make sure Espresso does not time out
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * 2, TimeUnit.MILLISECONDS);
        // Now we wait
        idlingResource = new ElapsedTimeIdlingResource(waitingTime);
        Espresso.registerIdlingResources(idlingResource);
    }

    public void cleanUp(){
        // Clean up
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
