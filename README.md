Welcome in my first Jetpack Compose project!

Here is a simple JetPack Compose Engine for datesim games.
You may use an database for creating a content inside. 

## Database

db.db file has at least 3 tables:
- Save_data
- Saved_environment
- Novell_data

Save_data and Saved_environment are used for uploading game progress when the game starts.

#### Novell_Data

Novell_Data is your main table. You should push all your dialogs, backgrounds and character emotions here.

##### set_mew_music
doesn't work yet

##### set_emotion(1 - 3)
change [Emotion](help/Emotion) of character in cell (1 - 3). Input - string, that included in when statement. 
![[fromStringToEmotion.png]]
In this statement emotions should be in lowercase.

Every character must has a links to emotion images.
![[testCharacter.png]]

##### delete_all_characters
Integer, if it equals 1, all characters on the screen disappearing 

##### delete_character
Input - number of cell (1, 2, 3). Else do nothing.
Character that standing in cell (1 - 3) disappears but saves the emotion of character. 

##### add_character(1 - 3)
Add or replace [character](help/Character) in cell (1 - 3). If you want to delete character - use delete character. **Input** - string, that listed in when statement in loveViewModel.fromStringToCharacter
![[fromStringToCharacter.png]]

##### set_new_background
**Input** - any Int, that listed in loveViewModel.fromDatabaseBackgroundToReal.
If Int isn't listed, engine tries to get Image by id.
![[fromDatabaseBackgroundToReal.png]]

##### speaker
**Input** - any string. Clears with next data.
![[speaker.png]]


##### dialog
**Input** - any Int that listed in loveViewModel.fromIntToDialog. Check [Dialog](help/Dialog) for more.
![[fromIntToDialog.png]]