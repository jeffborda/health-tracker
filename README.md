# Health Tracker

The Health Tracker is the first Android app that I am developing.  It emulates a Health app by focusing on things like exercise, but mainly it is a platform to learn how to develop an Android app.

## Lab 26 Features

* Added a button that can be tapped and a counter will display how many taps the user has made.  Encouraging messages will be displayed as the count get higher.
* A stopwatch was added to the main view, with start, stop, and reset buttons.  The stopwatch starts at 0:00:00.000 and goes up to 0:00:00.000.
* Image carousel was added which display different exercise-themed images.

### Screenshot

<img src="./assets/screenshot_1.png"
     width=150;  margin-right= 10px;/>



## Lab 27 Features
* Separated the Stopwatch and Finger Exercises onto different pages.
* Added a "Water Reminder" notification to be sent ever two hours on button click.
* Added buttons to the main activity to access the pages.
* Added a back button to return to the main activity from Stopwatch and Finger Exercises.
* Styled pages, added multiple buttons to a LinearLayout.
* Moved strings to the strings.xml file.

### Screenshots

<p float="left">
  <img src="./assets/screenshot_2.png" width="150" />
  <img src="./assets/screenshot_3.png" width="150" /> 
  <img src="./assets/screenshot_4.png" width="150" />
  <img src="./assets/screenshot_5.png" width="150" />
</p>


## Lab 28 Features
* Added Android Room Database.
* Adding new Exercise Diary entries to the database.
* Displaying Exercise Diary Entries with RecyclerView (Needs further styling).


### Screenshots

<p float="left">
  <img src="./assets/screenshot_6.png" width="150" />
</p>


## Credits

* Image carousel sourced from: https://github.com/sayyam/carouselview
* Video on TimerTask used to help develop the stopwatch: https://www.youtube.com/watch?v=36jbBSQd3eU
* More information about TimerTask utilized from: https://www.tutorialspoint.com/java/util/timer_scheduleatfixedrate.htm
* Setting up Alarm Manager: https://gist.github.com/BrandonSmith/6679223
* Setting up RecyclerView: http://www.vogella.com/tutorials/AndroidRecyclerView/article.html
