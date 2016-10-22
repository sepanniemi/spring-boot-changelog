#Spring Boot Change Log

Example spring boot application that exposes ```/changelog``` endpoint to view the contents of the CHANGELOG.MD file as a html page.
 
Has maven resources plugin configured to copy CHANGELOG.MD file from the standard root location to the build target directory to make it available in the classpath.

Path to CHANGELOG.MD file can be configured with a ```changelog.path``` application property.