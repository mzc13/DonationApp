# Donation App
An Android app for accepting in person credit/debit card donations using Square's Reader SDK.
## Authorization Step
Before the Reader SDK can be used, it must be initialized with an authorization code. The first time a user opens the app they will see this screen to enter a code.

![Authorization Activity](https://i.imgur.com/miLhVDP.png)

Pushing the "Get Code" button will open up a web browser and begin the authorization process. The PHP code used for the backend can be found at [github.com/mzc13/PHP-Code/tree/master/DonationApp%20Backend](https://github.com/mzc13/PHP-Code/tree/master/DonationApp%20Backend).

![Authorization website](https://i.imgur.com/FNQGwSy.png)

The button on the website will bring the user to a login page for Square where they will accept the permissions for the app.

![Square login](https://i.imgur.com/a19hUBJ.png)

After logging in and accepting the app permissions, the user is granted an authorization code. The button on this page will automatically copy the code for the user.

![Authorization code granted](https://i.imgur.com/eDtessl.png)

![Authorization code copied](https://i.imgur.com/eMAAi09.png)

The user can paste the code into the text box in the app. Pushing the submit button will bring the user to the official start screen of the app.

![Authorization code entered in the app](https://i.imgur.com/aIhCvPf.png)

## Using the App
If the Reader SDK has been previously initialized, the app will start on this screen.

![App start screen](https://i.imgur.com/I0OIccI.png)

Pushing the "Start Donation" button brings the user to a screen where they can enter the amount they would like to donate.

![Empty donation amount](https://i.imgur.com/EDoOJGM.png)

![10 dollar donation amount](https://i.imgur.com/YTkXVD1.png)

Pushing the continue button will bring the user to a screen where they can choose whether or not they would like to cover the transaction fee associated with using Square's services.

![Transaction fee prompt](https://i.imgur.com/iuKwVZz.png)

After making a decision, the user is brought to a confirmation page showing the total donation amount.

![Donation amount confirmation](https://i.imgur.com/L1zPDco.png)

Clicking the "Pay with card" button will open up a prompt directing the user to swipe their credit/debit card in the Square card reader connected to the Android device.

![Square transaction prompt](https://i.imgur.com/TEUbyFD.png)

After the payment is completed, the app goes back to the start screen, ready for another transaction.

![App start screen](https://i.imgur.com/I0OIccI.png)
