# Tennis-court-counter

This is my second app I've build during the training at Udacity.com.

The purpose for building this app is:
Work with more than one Layout (LinearLayout|RelativeLayout|ScrollView) and work with buttons.

The standard of the training was very easy, just add 1 or 2 or 3 to a score.
I wanted to make it more of a challenge and choose for this approuch.

Play tennis (best of three) and the app will keep track of everything if you just push the button of the one who scores.
The following is an example if player B scores:
Love - Love
Love - 15
Love - 30
Love - 40
Love - GAME
and automatically player B gets a point at the 'Score of the sets'.
For the next game to start, you'll have to push the button 'NEW GAME' and the game will be reset to 'Love - Love'.

It is possible that a tie occures at 40 points, we will call that: deuce.
After that you van have the following:
'adv - 40' or '40 - adv'.

Also, there is some calculating necessary for the sets!
A set will only be won:
- the first player to have 6 games (with a difference of 2 or more)
- else the first player to win 7 games.
The app will automaically start counting in the new set.

If you are completely done and want to reset everything, just push the button 'RESET'.

In the newer version of this app I've made a few adjustments:
- You can't rotate the app.
- You can fill-in the name of the player's

Enjoy!
