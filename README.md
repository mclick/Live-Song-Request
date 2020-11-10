# Live-Song-Request
Android App

Project Title/App Name:	Live Song Requests
Project Mission: 	A live song request voting app

The Problem:	To request a song at an event (wedding, ball, happy hour, homecoming, prom, club), attendees must either go up to the DJ, write it down with the hundred others, or (in modern times) tweet the DJ at the event. In a lot of cases, a good song is requested more than once. Some people see a requested song and change their mind.  Sometimes a song is requested that no one wants. The DJ then must scan the entire list to see if any stick out.

Project Summary:	Users can up-vote and down-vote song requests posted at a specific event. To make sure songs are not posted by users outside the event, a code will be displayed at the event to add / vote on the app. Once the code is correctly entered, a user can add songs and vote on other user posted songs. An admin who created the event (typically the DJ) can log into an existing event and delete the songs that were already played.

Final Project Description and Documentation

Data Storage:
The data stored in the app is represented in a Singleton design pattern located in EventList.java. This implementation was created to imitate database queries and storage for real app development. The hierarchy of data storage is polymorphic and shown here: EventList.java > Event.java > SongList.java > Song.java > Votes.java. State.java, an enum, is used to keep track of the user’s answer and is used throughout the data hierarchy. The data can be accessed by getting the Singleton EventList.getEventList() then performing whatever action is needed (.addEvent(), getEvent(postion), etc.). 

Testing Information:
A data entry testing method lives in MainActivity().java as addTestEvents(). This can be commented/uncommented in onCreate() at the tester’s discretion. If this is not uncommented, the user will see a blank screen upon app startup because no event has been added. To add an event, see below. The testing code adds 3 events and songs. There are no songs on the 3rd event so they can be added. 

Intents:
Intents are used to transition between Activities. For this app, all that is passed is the EventTitle (for this implementation, assuming this is a unique String, a database would have a key) so the we can locate the proper data per the activity happening. 

Event List Screen (app startup screen):
MainActivity.java, event_list.xml
Upon app startup, if testing data has not been entered from MainActivity.addTestEvents(), the screen will appear blank because no events have been entered. To add an event, click on the Options Menu > Add Event. Upon click, an intent is made to AddEventActivity().java. The event screen displays all active events added, one after the other. This is done by using a ListActivity BaseAdapter callen EventAdapter. Inside the getView() we set a listener per row on the button to join the event. 

Add Event Screen
AddEventActivity,java, activity_add_event.xml
This screen appears after “Add Event” is selected from the Event List Screen. Here we can add a new event to the Singleton. When an event is created, it takes an Event Name, the Event Code, the Admin Code, and a SongList(). A SongList is a class that keeps track of all the Songs at an Event. 
¬¬Event Code Screen
EventCodeActivity.java activity_event_code.xml
The Event Code Screen is reached after a user decides to join an event from the Event List Screen. The point of this screen is to make sure users not at the event are not able to access the SongList for this event. An event code will be displayed at the event to allow only guests present. A future feature can limit the display on an event if you are not in the immediate area to ensure the SongList is protected from guests who pass along the code to non-attendees. After the code has been entered it calls to SongActivity to decide if the code is correct. SongActivity then decides to let the intent happen (if the code is correct) or to finish() (if the code is incorrect). It will also output a toast saying that the code is incorrect. Event Code does display the Event Name so you know you are on the correct event. Upon proper validation from SongActivity, SongActivity continues. 

Song Activity Screen (List)
SongActivity.java, MyView.java activity_song.xml
The Song Activity Screen is queued upon proper validation of Event Code. Validation is performed within the Song Activity Screen for protection of the event code.  Upon proper validation, the Song Activity continues and generates another custom adapter. In this adapter, we use a 2D canvas to draw the up and down arrows (they are not images). They use a custom class called MyView.java which performs the line segments of the arrows including the paint color. When forming the row, we need to create two listeners to the arrows up and down. MyView was designed to handle these two cases along with the two cases of color. 

Voting up on the song will increase the vote to 1 but no more. It will change the color of the arrow through MyView so you know that increase was because of you, not some other user, and it also increase the vote inside the Song inside the SongList. The same goes for if the down arrow is clicked. If you click the up arrow on an already selected up arrow (red) or the already selected down arrow (red), the vote will undo, decrease and turn back to black. The same goes for if the down arrow is clicked. You can negatively vote a song request. Other users will be able to vote so you can have many numbers on songs. 

Also, displayed on the row is: the song title, artist, and user notes. Right now, there is no defined character limit, allowing users to input long notes on songs. Users are not able to delete songs, only vote on them. From the options menu, users can Add Song which will queue SongRequest.java with an intent. Super users (admins who created or manage the event) can enter the admin portal my click on the admin row from the Options Menu. This will send an intent to AdminCodeActivity.java for validation of the user.

Song Request Activity Screen
SongRequest.java, activity_song_request.xml
The Song Request Screen appears once an intent comes from the Song List Screen on a specific SongList. Here you are able to input a new Song to SongList. There are the fields: name, artist, and notes. Under notes, users can imput messages about the reason they are requesting it – it makes it more fun. You can choose to make another song request or just submit it and return to the updated song list. 

When a song is created, it said total votes to 0. It also creates a new instance of Vote.java. Vote keeps track of the user’s vote on the song: if voted up, down, and their colors. 

Admin Event Code Screen
AdminCodeActivity.java, activity_admin_code.xml
The Admin Event Code Screen can be reached through the Song Activity List Screen as an Option. This is similar to how the Event Code Screen operates. The reason for this screen is to give admins access to delete songs that were played already. When the event was created, it asks you for the admin code, this is the place to use the password. Typically, an admin may be the DJ or the Venue’s staff. The code should be kept secret so the event does not get compromised. After the code has been entered it calls to AdminActivity.java to decide if the code is correct. AdminActivity then decides to let the intent happen (if the code is correct) or to finish() (if the code is incorrect). It will also output a toast saying that the code is incorrect. Admin Event Code does display the Event Name so you know you are on the correct event. Upon proper validation from AdminActivity, AdminActivity continues. 

Admin Activity Screen
AdminActivity.java, recycle.xml, activity_admin.xml
The Admin Activity Screen is a place where admins can delete already played songs. This is queued from the Admin Event Code Screen. Upon proper validation from Admin Activity, it will continue or finish() with a toast on “incorrect code.” To delete a song, you must use a sliding gesture to the left. This will make a red animation appear with an X. Upon full swipe, the song will be removed from SongList and refresh.

This is possible through a recycler view in recycle.xml that has activity_admin.xml rows. They layout is set to start with recycle.xml and then have a SongAdapter() create the rows with binding ViewHolder(row) objects. We have an animationDevoratorHelper() to arrange the sliding animation and setUpItenTouchHelper() to set the colors, “X”, and swipe. 

 

Final Project Discussion

API Features
I used two main API features included in this project. The first one is that the arrows in SongActivity.java and activity_song.java are drawn in a 2D plane each time. activity_song.xml is the design per each row. A 2D plane drawing was included in the lecture but the implementation is a bit different.  Inside activity_song.xml we declare two instances of com.example.clikentertainment.live.MyView  to allow for arrow up and arrow down drawing capabilities. They are dynamically updated on their color from MyView.java. MyView controls these objects, draws their line path, gives them color, and declares if it was touched. They are given their Path of Lines in MyView.java but called by SongActivity.java so we can setup listeners. MyView.java will trigger its onTouchEvent() upon touch so we @override it and call the super class so SongActivity.java is notified. Upon notification of it being triggered we run logic to update the arrows and Singleton with all the data (if needed). 

The second API feature I used was being able to swipe and delete with animation. Inside AdminActivity.java we create and set a RecyclerView recycle.xml as the layout. We then construct an adapter to the view to give it activity_admin.xml rows per size of SongList(). We setup an ItemTouchHelper and Item.Decoration to handle touch, swipe, and animation on the swipe. Through the adapter though we have a ViewHolder per Song of SongList which allows us to create these rows with the helper and animation. RecyclerView and a similar adapter was included in the lecture but this implementation is noticeably different.

Challenges
It was hard to get both API features working at first. I had a hard time understanding the animation and ItemTouchHelper() at first but I knew I wanted this feature included in my design. I eventually got it working by creating that ViewHolder class to hold the objects in the adapter which then uses the scheme laid out. 

Another difficulty was getting the arrows to work. When I clicked the up arrow on one row, they would all turn red. It took me a couple of hours to figure out the issue. Apparently the logic was being created separately so that is why it worked, but upon refresh() the arrows up or arrows down, would only ready one instance. I figured it out by having MyView.java handle the colors and not an outside SongColor.java class. 

Limitations
In earlier implementation of Event Code Screen to Songs Screen, I wanted to have the intent go to a polymorphic Security intent which would make an intent to Songs Screen upon proper validation. This is so the hacker cannot start up an intent to Songs Screen without the correct code altogether. The Security Screen would also limit the amount of tries and automatically block quick answers (like from a bot spamming with characters to guess the password). 

The other limitation I had was getting the logic and data storage of the arrows correct. I had a Votes class with Votes with an Answer that was not stored with the Event. I did this to allow for a Users class and implementation (so you could sign in i.e. log in with Facebook) and see your answers to the Songs. I did end up figuring it out by writing another Singleton class with Users with have a name, username, and password along with Votes > Vote > Answer Classes for data objects. Though this implementation was many more Activities and design I didn’t have time for. 

 

Mobile Application Development Experience

One limitation I think Android Studio and the SDK should include is an easier way to implement the obvious modern features of an app. The floating action button on the bottom of the screen, easier way to implement and customize a ListView, and a 2D shape generator where you can draw these designs rather than code it. There are so many ways to expand this and make it even easier for beginners and advanced developers. I believe there are other platforms out there that can handle things like this so it surprises me that Android Studio is not on the edge of new development. 

I personally ran into the issue of implementing the RecyclerView with an Adapter to display a list and then put an action and animation on that row. It is a little difficult to understand at first and there is still a lot to learn. I feel there are much easier ways to do that that make more sense rather than trying to work with the Java language universal syntax. I am excited to see the development of Kotlin in Android. I believe Kotlin will succeed Java, especially when it comes to findViewById()!

Android’s SDK has very ambiguous errors. Sometimes it took me a good amount of time to figure out what it meant and where the error was. I feel this could be a beneficial improvement for the future development that would save developers a lot of time. When you type a lot of code or edit something, especially when it is an error in the manifest, it is very default to track down. I was able to narrow the error reports with the help of the internet.

I did have a positive experience learning Android Studio’s platform tools. Using a constraint layout to drag and drop elements made building simple layouts much easier versus typing all that information. Being able to connect the elements to each other and within the screen limitations seems robust for future devices. The blueprint layout and view of the app made designing the front end a breeze. Overall, making things simple with the same customizations is always a challenge, but android was able to do this part well. 

Another positive experience was learning about intents. Being able to call other apps or other local activities was easier than I thought once the user grants the permissions. Though it opens your eyes of how a regular app may request permissions it doesn’t necessarily need to run and can capture information you don’t want it to. Intents were a new messaging object I wasn’t able to tackle in the past and I do like the overall structure in how you need one to perform actions on android. I like the framework that the app stays in its sandbox for security purposes and needs additional permissions to set intents elsewhere. 

My experience with this class has opened my eyes to a whole new world of Java. I enjoy Java, always have, so it was great to program in the same way but for a different purpose. I feel confident enough to say I know how to build a basic app. I’m looking forward to continuing in app development next term. 
