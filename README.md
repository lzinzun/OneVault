# OneVault
OneVault is a personal encryption application where users can encrypt personal data (passwords, messages, etc.), with various levels of encryption, and store it at local device.



There is no commercial intent for this application, this is wholy a recerational project.

## How to Run the App in Android Studio
1. Clone or download the sauce to desired directory
2. Import project to Android Studio, wait for Gradle to handle dependecies and load project
3. Set up Android Studio's default emulator (for quick access)
4. Press on `Run 'app'` play button

## Instructions for Use
1. First Sign Up, if this is first time use
2. Enter an email and a Master password (write this one down, uber important)
3. Login with same credentials as previous step
4. After successful login, you are able to add accounts by pressing the `+` button
5. At next Activity, enter name of the account to store and its password
6. Once encrypted you will see a CardView of the accounts you will be adding, with name and a pseudo password
7. By clicking the CardView you have the option to Delete, Edit, or Reveal the encrypted data after entering Master password once more

## User Stories (as of Sep. 2)
- User is able to enter account and its passowrd to encrypt data
- User is able to decrypt data back if Master password provided
- User is able to delete an account
