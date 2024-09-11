# Shop Verse - Product Management App

## Overview

Shop Verse is a product management app that helps users browse, search, and interact with products. The app uses a modern and user-friendly interface. It features a navigation system built with Android's Navigation Component, making it easy to move between screens.

## Navigation Structure:

- The app consists of several screens (Fragments) that users can navigate between using the Navigation Component with destinations and data passed via Safe Args.

## Splash Screen Flow

- **SplashFragment**: The initial screen displayed when the app is launched.
- Action: Navigates to either WelcomeFragment or LoginFragment or HomeFragment based on the user’s status.

## Authentication Flow

- **WelcomeFragment**: A welcome screen providing option to get started
- Action: Navigates to LoginFragment.
- **LoginFragment**: The login screen for user authentication.
- Action: Navigates to SignUpFragment if the user doesn’t have an account.
- **SignUpFragment**: The sign-up screen for new users.
- Action: Returns to LoginFragment after registration.

## Main App Flow

- **HomeFragment**: The main screen displaying a list of products.
- Action: Navigates to ItemFragment to view product details.
- **ItemFragment**: The product details screen, receiving multiple arguments such as images, title, description, price, rating, and stock information.
- **FavoriteFragment**: The favorites screen where users can see their saved favorite products and remove it if they want.
- Action: Navigates to ItemFragment to view detailed information about a favorite product.
- **ProfileFragment**: The user profile screen.
- **SearchFragment**: The search screen where users can look for products.
- Action: Navigates to ItemFragment to view the selected product’s details.

## Details in ItemFragment

- **images**: List of product images.
- **title**: Product title.
- **description**: Product description.
- **category**: Product category.
- **availabilityStatus**: Availability status of the product.
- **discountPercentage**: Discount percentage.
- **price**: Product price.
- **warrantyInformation**: Warranty details.
- **stock**: Stock quantity.
- **rating**: Product rating.
- **weight**: Product weight.

## Technologies Used

- **Kotlin**: Primary programming language.
- **MVVM (Model-View-ViewModel) Architecture**: Clean architecture pattern.
- **Retrofit**: Networking library for API calls.
- **Room Database**: Local storage for favorite recipes.
- **Glide**: Image loading library.
- **Data Binding**: Simplifies UI updates.
- **Navigation**: Android Navigation Component

## Installation

1. Clone the repository:

   ```bash

   ```

2. git clone https://github.com/mariam021/Shop-Verse
3. Open in Android Studio: Open the project in Android Studio.
4. Sync the project with Gradle files.
5. Build the project: Ensure all necessary libraries are downloaded and build the project using Gradle.
6. Run the app: Run the app on an emulator or Android device.

## Screenshots

### Splash Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/splash.png" alt="Splash Screen" width="300"/>

_Splash Screen is the introductory screen displayed when the app is launched. It typically shows a logo before navigating to the Welcome or Login screen._

</div>

### Welcome Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/welcome.png" alt="Welcome Screen" width="300"/>

_A welcome screen that greets users and provides an option to get started_

</div>

### Login Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/login.png" alt="Login Screen" width="300"/>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/exit.png" alt="Exit Login Screen" width="300"/>

_A login screen where users can enter their credentials to log in to the app. It includes options for logging in with a username and password, and a link to sign up if the user doesn't have an account._

</div>

### SignUp Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/signup.png" alt="SignUp Screen" width="300"/>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/signup%20validation.png" alt="Validation SignUp Screen" width="300"/>

_A signup screen for new users to create an account. It collects necessary details like name, email, and password, allowing users to sign up and then log in to the app.._

</div>

### Home Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/home.png" alt="Home Screen" width="300"/>

_ A home screen is a main screen that displays a list of products. Users can browse available products and select any product to view its details_

</div>

### Search Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/search%20result.png" alt="Search Screen" width="300"/>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/search%20empty.png" alt="Empty Search Screen" width="300"/>

_A search screen allows users to search for products by entering keywords. Users can view search results and select a product to view its details._

</div>

### Favourites Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/favorite.png" alt="Favorites Screen" width="300"/>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/favorite%20empty.png" alt="Empty Favorites Screen" width="300"/>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/remove%20favorite.png" alt="Remove From Favorites Screen" width="300"/>
  
_A favorites screen that shows a list of products the user has marked as favorites. Users can access their favorite products quickly and view their details and can remove it if they want_
</div>

### Details Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/details.png" alt="Details Screen" width="300"/>

_A Details screen displays detailed information about a selected product. It shows product images, descriptions, price, availability, and other details_

</div>

### Profile Screen

<div>
<img src="https://github.com/mariam021/Shop-Verse/blob/dev/screenshots/profile.png" alt="Profile Screen" width="300"/>

_A profile screen where users can see their information. It might include an options for logging out._

</div>

### Contact Us

If you have any questions, feedback, or suggestions, feel free to reach out to us at:

- [Mariam Mohamed](mailto:mm5253@fayoum.edu.eg) [Github](https://www.github.com/mariam021)

- [Malak Hussein](mailto:malakhuseein635@gmail.com) [Github](https://github.com/Malak-gif512)

- [Mohamed Hany](mailto:mohamedhanyomar75@gmail.com) [Github](https://www.github.com/mohamedhanyomar2003)

- [Mazen Abdel-tawwab](mailto:mazen110.net@gmail.com) [Github](https://www.github.com/M-a-z-e-n)

- [Ahmed Nabil](mailto:Amohdaly240@gmail.com) [Github](https://www.github.com/AhmedMohdal)

## License

MIT License. You are free to use and modify the project as needed.
