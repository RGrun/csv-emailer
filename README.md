# csv-emailer

CSV Emailer is a small tool for sending promo codes to a large list of users using a standard SMTP server.
It's primary usefulness stems from the fact that emailing codes to a large list of recipients manually is a huge pain; this tool 
simplifies the process.

CSV Emailer exists because I ran a beta test for my Android game [Lunar Invasion](https://play.google.com/store/apps/details?id=r3software.org.lunarinvasion)
and needed a way to send free download codes to the testers once the game was finished. That is, I thought I was going to. Turns out
Google Play dosen't let app developers generate free download codes like I thought it did. I learned this after I wrote
this emailer, however, so I simply substituted the promo codes with a link to download the game directly from my server instead.
I decided to publish this to GitHub because I figured it might be a useful tool to someone else with a similar problem.


## Installation

Simply clone this repo. You'll have to do a little configuration in the `core.clj` file after installation.

You'll need the Clojure build tool Lienigen to use CSV Emailer.

## Usage

1. Name your CSV file `names.csv` and place it in the project's `resources` directory. The file should be in the following
format: "`email-address,code`" with one CSV pair per line. See `names.example.csv` to see how this is done.
 
2. Modify the maps in `core.clj`, substituting in your SMTP server's information. 

3. Create the HTML template for your email's body. Somewhere in this file should be the token `[CODE]`, this token will
be replaced by the promo code for that recipient. This template should be named `template.html` and be placed in the 
`resources` directory. See `template.example.html` if you want to see how this is done.

4. `cd` into the project's directory from the command line and type `lein run`. 

## License

Copyright © 2015 Richard Grunert

You may use this tool for whatever purpose you wish.
