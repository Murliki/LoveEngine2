To make dialog in the game, first of all you should add an number of dialog in when statement in LoveViewModel - fromIntToDialog
![[fromIntToDialog.png]]
Then you should create a dialog item in classes/MenuDialog, in companion object.
![[MenuDialog.png]]
Choices are just a strings, that user will see when he take the dialog. They can be any string.
onClicks are logic for the dialog.
ChangeBranches, if they aren't zero, globally push new dataNumber in viewModel.
