# booking-api-part-2-Samuelg108
booking-api-part-2-Samuelg108 created by GitHub Classroom

**App Evaluation:**

*Category:* The Online Hotel Booking app falls under the travel and hospitality category, specifically designed to simplify hotel reservations for users.

*Story:* The app's narrative centers around making hotel booking convenient and tailored to users' preferences, enhancing their travel experience by connecting them with suitable accommodations.

*Market:* Targeting travelers of all ages, the app caters to individuals seeking seamless accommodation reservations across various destinations, aligning with diverse travel needs.

*Habit:* The Online Hotel Booking app encourages users to engage regularly by offering a user-friendly platform for discovering and booking accommodations, fostering a habit of efficient and enjoyable travel planning.

**Scope:**

We aim to create an application allowing users to effortlessly find and book hotels based on their preferences. This app is designed to eliminate the challenges of finding suitable accommodations, offering quick matching and booking functionalities. Users can seamlessly connect with their chosen hotels, making the process hassle-free and enhancing their overall travel experience.

**Product Spec User Stories (MVP):**

1. *User Registration:* Users can create an account using their email or phone number.
2. *User Profile:* Users can set up a profile with their name, preferences, and travel history.
3. *Location Detection:* The app detects the user's current location based on GPS or user input.
4. *Hotel Discovery:* Users can browse and discover hotels based on destination, travel dates, and preferences.
5. *Booking:* Users can book accommodations, review booking details, and receive confirmation.
6. *Messaging:* Users can communicate with hotels for inquiries or special requests.
7. *Notifications:* Users receive notifications for booking confirmations, check-in reminders, and special offers.
8. *Privacy Settings:* Users can control their privacy settings, including notification preferences.

**Optional User Stories (Enhancements):**

1. *Review System:* Implement a review system where users can leave feedback and ratings for hotels.
2. *Personalization:* Allow users to save favorite hotels, preferences, and receive personalized recommendations.
3. *Discounts and Offers:* Integrate a feature for discovering and applying discounts or special offers.
4. *Travel Itinerary:* Include a feature for users to plan and organize their travel itineraries.
5. *Integration with Travel Services:* Connect the app with additional travel services such as transportation and activities.
6. *Multi-language Support:* Provide support for multiple languages to accommodate international users.
7. *Virtual Tours:* Include virtual tours of hotel rooms and facilities for a more immersive booking experience.
8. *Accessibility Features:* Ensure the app is accessible to users with disabilities.

**Screens:**

1. *Registration Screen:* Users can create an account using their email or phone number.
2. *Login Screen:* Existing users can log in to their accounts.
3. *User Profile Screen:* Users can set up and edit their profiles, including preferences and travel history.
4. *Location Detection Screen:* The app detects the user's current location based on GPS or user input.
5. *Hotel Discovery Screen:* Users can browse and discover hotels based on destination, travel dates, and preferences.
6. *Booking Screen:* Users can view available hotels, review details, and confirm bookings.
7. *Messaging Screen:* Users can communicate with hotels for inquiries or special requests.
8. *Notifications Screen:* Users receive notifications for booking confirmations, check-in reminders, and special offers.
9. *Privacy Settings Screen:* Users can control their privacy settings, including notification preferences.

**Navigation:**

*App Bar Tabs:*
1. *Home Tab:* Displays a feed of hotel recommendations and recent booking activity.
2. *Profile Tab:* Users can access their own profile, edit information, and view booking history.
3. *Messages Tab:* Users can access their communications with hotels, making inquiries or special requests.

*Navigation Flows:*
1. *Registration Flow:* Registration Screen -> Home Tab (after successful registration).
2. *Login Flow:* Login Screen -> Home Tab (after successful login).
3. *Profile Editing Flow:* Profile Tab -> Edit Profile Screen. After editing, return to Profile Tab with updated information.
4. *Hotel Discovery Flow:* Home Tab -> Hotel Discovery Screen. Users can browse and discover potential hotels.
5. *Booking Flow:* Hotel Discovery Screen -> Booking Screen. Users can view details and confirm bookings. After booking, return to Home Tab.
6. *Messaging Flow:* Home Tab -> Messages Tab. Users can access their conversations with hotels. Messaging with a hotel opens the Messaging Screen.
7. *Notifications Flow:* Users receive push notifications for booking confirmations and special offers. Tapping a notification opens the relevant screen (e.g., Booking or Messages).
8. *Privacy Settings Flow:* Profile Tab -> Privacy Settings Screen. Users can adjust privacy settings.

**Schema Models:**

*Users:*
- UserID: String (unique identifier)
- Name: String
- Email: String
- Preferences: Object (travel preferences)
- TravelHistory: Array of Objects (previous bookings)

*Bookings:*
- BookingID: String
- UserID: String
- HotelID: String
- CheckInDate: Date
- CheckOutDate: Date
- RoomType: String
- Price: Double
- Status: String (confirmed, pending, canceled)

*Hotels:*
- HotelID: String (unique identifier)
- Name: String
- Location: Object (Latitude, Longitude)
- Amenities: Array of Strings
- Ratings: Object (overall, cleanliness, service, etc.)
- Contact: String (hotel contact information)

*Messages:*
- MessageID: String
- UserID: String (sender)
- HotelID: String (receiver)
- Content: String
- Timestamp: Timestamp

*Notifications:*
- NotificationID: String
- UserID: String
- Type: String (booking confirmation, special offer, etc.)
- Content: String
- Timestamp: Timestamp

**Networking:**

*Home Feed Screen:*
- (Read/GET) Query hotel recommendations: Retrieve a list of hotels based on user preferences and recent booking activity.

*Booking Screen:*
- (Create/POST) Book a Hotel: Allow users to confirm bookings, updating the database with the new booking details.

*Messaging Screen:*
- (Read/GET) Query message history: Retrieve the message history for a specific user or hotel.
- (Create/POST) Send a message: Allow users to send messages to hotels for inquiries or special requests.

*Notifications Screen:*
- (Read/GET) Query user notifications: Retrieve notifications for the logged-in user, such as booking confirmations and special offers.

*Settings Screen:*
- (Read/GET) Query user settings: Retrieve and display user-specific settings, such as notification preferences and travel history.
- (Update/PUT) Update user settings: Allow users to update their settings, including notification preferences and other app-related configurations.
