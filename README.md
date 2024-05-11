# LinkLobby
LinkLobby is a plugin to easily learn more about other players! Learn more about the people you are playing with, and tell the world about who you are! Easily connect with others via Discord, Instagram, etc to make long-lasting friendships!
## Commands
You can customize the "/friend" command itself and the aliases. See the [config.yml](https://github.com/gwerrry/SimpleFriends/blob/0b0e0e4649cc6b63be2b20f1fae0a1d3f07036ca/src/main/resources/config.yml) "friend_cmd" section for a more clear understanding of what is customizable.
- ```/linkl``` | displays commands
- ```/linkl categories``` | shows all available categories
- ```/linkl add <category> <description>``` | sets the specified <category> to the <description> (leave description blank to clear the category from profile)
- ```/friend public``` | make your profile public
- ```/friend private``` | make your profile private
- ```/friend show``` | view your own profile
- - ```/friend show <username>``` | view specified user's profile, if publicized

## Config
**Custom Categories**
By default, the categories are name, age, discord, status, gender, pronouns
Steps to add custom categories.
1. Load the plugin
2. Locate the plugins/LinkLobby/linklCategories.txt file
3. Enter your categories, separated by line, case-insensitive
The order of which the categories will be displayed will be in the same order as the .txt file

## Notes
Future Updates:
- Multiple Version Support
- Customizable Color for Profile
- Option to use GUI instead of Chat
- MySQL/SQLite integration
- Clickable Links
- Blacklist Words
- Profile Views (How many times you have been viewed)
- More!
