
# FoodGreen

## Identification
###### Chintan Patel (chintan.patel@dal.ca) - B00826089
###### Jeel Gondaliya (jl251073@dal.ca) - B00819827
###### Shrey Amin (shrey.amin@dal.ca) - B00822245
###### Tirtha Modi (Tirtha.Modi@dal.ca) - B00826404
###### Zeel Patel (zl336947@dal.ca) - B00824757

## Project Summary
On Facebook, there are several community groups where people connect with each other. They make a post on different topics which provide useful information. There are many people who want to sell their food dishes or start their catering service. So, they put an advertisement regarding their food item along with its price and description. The problem is that the buyer has to make comments if they are willing to buy the dish and has to pass through a tedious process of ordering food. To address this problem, we propose a user-driven centralized platform where people can buy and sell their homemade dishes at an affordable rate. The application differs from other food ordering platform which provides ready-made restaurant food. The target audience of our application will be immigrants and international students as they have fewer options for their homemade food. Moreover, this can be an ideal platform for those people who are diet and health conscious.  

![Home page image](http://foodgreen.000webhostapp.com/home_page.png)

## Libraries
[1] android-upload-service: This library is used to upload images to the server from the application. When the seller wants to add new order, the functionality to upload image from gallery is added using this library. We are storing image to the server and image name in the firebase. Source: [https://github.com/gotev/android-upload-service] 

[2] picasso: This library is used to show images from server in the application. Using the image name from firebase, the application fetches image from server and shows in activity using picasso. Source: [https://github.com/square/picasso] 

[3] firebase: This library is used to setup and process data with firebase. As the application needs to work with real-time data, we have used firebase to store the data. Source: [https://firebase.google.com/docs/android/setup] 

## Installation notes
To install this application, no extra files are needed to add. It is recommended to give all the permissions after installing the application to run the application smoothly. 

## Code examples
### Problem 1
##### Food Categories 
We were facing problems deciding food categories of the dishes. As there was no specific food category list already available and we did not want to restrict our application to specific food categories, we decided to give the user an option to add a new food category. Say for instance, when the user adds a new order for buying or selling the food item, food categories which are already available are being shown in the application. If the desired food category is not available, the user can add a new food category and that food category will be stored in the list of food categories. 

##### Code example
###### Inside the method to fetch food categories from firebase 

food_categories.add(ds.child(String.valueOf(i)).getValue(String.class)); 

###### new food category which entered by the user 

food_category_choice = new_food_category.getText().toString(); 

###### insert new category in the firebase 

DatabaseReference update_food_category = FirebaseDatabase.getInstance().getReference("food_categories").child(food_category_key); 
update_food_category.child(String.valueOf(count)).setValue(food_category_choice); 

### Problem 2
##### Image
We were facing problem deciding how and where to store the image which will be uploaded by the seller while making a new order. We found that the Base64 string can be used to store the image in the firebase. But using this option could be very complicated and time-consuming. Thus, we decided to store the image in the server and storing its path in the firebase.  

##### Code example
###### uploading image to server using android-upload-service 

new MultipartUploadRequest(this, UPLOAD_URL) 
.addFileToUpload(path, "image").addParameter("name", name).startUpload(); 

where path is the local path of the image where it is stored. Name is the name which will be given to the image in the server. And it will be stored in the firebase. 
 
###### showing image from the server using picasso 
Picasso.get().load("http://foodgreen.000webhostapp.com/images/" + images[position]).into(mImageView); 

where mImageView is the imageview where the image will be shown.  

## Feature section
### buyer module
This is one of the main features where buyers can buy the dishes that every seller has posted. Additionally, every buyer has an option to place a new order of an item and find a potential seller for it.  While placing an order a buyer has to enter food category, dish name, description and other details regarding the food item.

### Seller module
Sellers can see the post of food dishes created by the buyer. Every seller has an option to make a bid of the dish. As a result, the buyer will have several options for food dishes with a different price. Additionally, the seller can provide a new dish using the new order option. The seller can submit food name, the category of the food, image and other order related details.

### Filter
Our application provides a filtered view using which a user can select specific options of the food dishes. The user can filter food dishes based on the food category and price. Thus, the user can see only specific dishes which fulfil the selected criteria. 

### Uploading image
This feature allows the seller to upload the image of the food dish. This image will be stored in the server and the path will be stored in firebase. The image is shown in the activity using Picasso library. 

### SMS Notification
Our application allows buyers to place an order based on their convenience. Once the order is confirmed, a notification about the order will be sent to the seller through SMS. The message will contain personal and contact information of buyer and seller. When the seller adds a bid to the order of buyer, an SMS notification is sent to the buyer about the bid.

### Database
Firebase is used to store real-time data generated from the application. It will also store account details of buyer and seller. Moreover, details of food dishes and orders placed will be fetched dynamically and displayed on the user screen.  

## Final project status
### Minimum functionality  
[1] The application will display orders and requests for different dishes. (completed)  
[2] The buy option will allow users to add dish name, quantity, food category and other order related details. (completed)   
[3] The sell option will allow users to add dish name, price, food category and other order related details. (completed)

### Expected Functionality  
[1] The user can apply a filter on specific food items based on price and category of dish. (completed) 
[2] Users will get notifications based on updates in the order. (completed)  

### Bonus Functionality  
[1] The application will provide a chat feature to carry out negotiations if any and can have a general discussion with another user. (Not implemented)  
[2] Users will get personalized recommendations of dishes based on their buy and sell history. (Not implemented)  
[3] Payment module will be integrated which will provide benefit to make direct payment using our application. (Not implemented) 

## Sources
[1]   "Android Studio Tutorial - Order Foods Part 1 ( SignUp , SignIn and Welcome Screen)", YouTube, 2019. [Online]. Available: https://www.youtube.com/watch?v=Ad41Bh704ms&list=PLaoF-xhnnrRW4lXuIhNLhgVuYkIlF852V. [Accessed: 16- Feb- 2019]. 

[2]   "Android Sending SMS", www.tutorialspoint.com, 2019. [Online]. Available: https://www.tutorialspoint.com/android/android_sending_sms.htm. [Accessed: 06- Mar- 2019]. 

[3]   "[Android Example] Pick Image from Gallery or Camera", AndroidClarified, 2019. [Online]. Available: https://androidclarified.com/pick-image-gallery-camera-android/. [Accessed: 11- Mar- 2019]. 

[4]     H. android? and F. Puffelen, "How to get child of child value from firebase in android?", Stack Overflow, 2019. [Online]. Available: https://stackoverflow.com/questions/43293935/how-to-get-child-of-child-value-from-firebase-in-android. [Accessed: 02- Mar- 2019]. 

[5]    "Read and Write Data on Android  |  Firebase Realtime Database  |  Firebase", Firebase, 2019. [Online]. Available: https://firebase.google.com/docs/database/android/read-and-write. [Accessed: 27- Feb- 2019]. 

[6]    B. Khan, "Android Upload Image using Android Upload Service", Simplified Coding, 2019. [Online]. Available: https://www.simplifiedcoding.net/android-upload-image-to-server/. [Accessed: 14- Mar- 2019]. 

[7]   "Listview image with text in android", YouTube, 2019. [Online]. Available: https://www.youtube.com/watch?v=_YF6ocdPaBg. [Accessed: 13- Feb- 2019]. 

[8]   GitHub. (2019). gotev/android-upload-service. [online] Available at: https://github.com/gotev/android-upload-service [Accessed 16 Mar. 2019]. 

[9]   H. [duplicate], F. Ahmed and A. Gosemath, "How to show image from server to my ImageView in Android", Stack Overflow, 2019. [Online]. Available: https://stackoverflow.com/questions/40503253/how-to-show-image-from-server-to-my-imageview-in-android. [Accessed: 17- Mar- 2019]. 

[10]   "square/picasso", GitHub, 2019. [Online]. Available: https://github.com/square/picasso. [Accessed: 17- Mar- 2019]. 

[11]   "Add Firebase to your Android project  |  Firebase", Firebase, 2019. [Online]. Available: https://firebase.google.com/docs/android/setup. [Accessed: 27- Feb- 2019]. 

[12]   A. bar et al., "Android - styling seek bar", Stack Overflow, 2019. [Online]. Available: https://stackoverflow.com/questions/16163215/android-styling-seek-bar. [Accessed: 02- Mar- 2019].

[13] A. Chugh, "Android Date Time Picker Dialog - JournalDev", JournalDev, 2019. [Online]. Available: https://www.journaldev.com/9976/android-date-time-picker-dialog. [Accessed: 12- Feb- 2019]. 

[14]   T. Dawson, "Vegan Spicy Ramen Noodles - Love is in my Tummy", Love is in my Tummy, 2019. [Online]. Available: https://loveisinmytummy.com/2016/11/vegan-spicy-ramen-noodles.html. [Accessed: 23- Mar- 2019]. 

[15]  "Steam Workshop :: arghhh", Steamcommunity.com, 2019. [Online]. Available: https://steamcommunity.com/sharedfiles/filedetails/?id=630725432. [Accessed: 18- Mar- 2019]. 
