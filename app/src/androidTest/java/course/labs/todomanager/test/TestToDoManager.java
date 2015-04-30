package course.labs.todomanager.test;

import course.labs.todomanager.ToDoManagerActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TestToDoManager extends ActivityInstrumentationTestCase2<ToDoManagerActivity> {
  	private Solo solo;
  	
  	public TestToDoManager() {
		super(ToDoManagerActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
		solo.waitForActivity(course.labs.todomanager.ToDoManagerActivity.class, 2000);
        //Click on Add New ToDo Item
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.footerView));
        //Wait for activity: 'course.labs.todomanager.AddToDoActivity'
		assertTrue("course.labs.todomanager.AddToDoActivity is not found!", solo.waitForActivity(course.labs.todomanager.AddToDoActivity.class));
        //Enter the text: 'title'
		solo.clearEditText((android.widget.EditText) solo.getView(course.labs.todomanager.R.id.title));
		solo.enterText((android.widget.EditText) solo.getView(course.labs.todomanager.R.id.title), "title");
        //Click on Done:
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusDone));
        //Click on Not Done
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusNotDone));
        //Click on High
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.highPriority));
        //Click on Choose Date
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.date_picker_button));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Enter the text: '01'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 1));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 1), "01");
        //Enter the text: 'May'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "May");
        //Click on Done
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Choose Time
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.time_picker_button));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);

        //Enter the text: '10'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 1));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 1), "10");
        //Click on Done
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Submit
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.submitButton));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusCheckBox));
        //Click on Add New ToDo Item
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.footerView));
        //Wait for activity: 'course.labs.todomanager.AddToDoActivity'
		assertTrue("course.labs.todomanager.AddToDoActivity is not found!", solo.waitForActivity(course.labs.todomanager.AddToDoActivity.class));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.title));
        //Enter the text: 'title2'
		solo.clearEditText((android.widget.EditText) solo.getView(course.labs.todomanager.R.id.title));
		solo.enterText((android.widget.EditText) solo.getView(course.labs.todomanager.R.id.title), "title2");
        //Click on Done:
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusDone));
        //Click on High
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.highPriority));
        //Click on Choose Date
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.date_picker_button));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Enter the text: '01'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 1));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 1), "01");
        //Enter the text: 'May'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "May");

        //Click on Done
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Choose Time
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.time_picker_button));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Enter the text: '23'
		solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));
		solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "23");
        //Click on Done
		solo.clickOnView(solo.getView(android.R.id.button1));
        //Click on Reset
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.resetButton));
        //Click on Cancel
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.cancelButton));
        //Press menu back key
		solo.goBack();
	}
}
