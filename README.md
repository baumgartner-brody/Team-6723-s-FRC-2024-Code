To make changes to this repo, you should be able to run the following commands: 

`git init` - Run this command in your work folder from the VS Code terminal.

`git remote add origin https://github.com/baumgartner-brody/Team-6723-s-FRC-2024-Code.git` - Configure the remote repo.

`git fetch` - This will pull all commits from the remote repo. One of the previous steps should have made you sign in to github using either a GUI or the `git config` terminal interface.

`git branch mynewbranch` - This will create a new branch called "mynewbranch".
`git checkout mynewbranch` - This will check that branch out for you. 

Alternatively, you can run `git checkout -b mynewbranch` to create / checkout a branch. 
This does both of the above commands in one line.

`git pull` - If you're working on a branch that someone else is working on, run this to stay up to date with their commits.

After you've made significant changes to the code:

`git add *` or `git add <some specific file>` - This will "stage" your changes so they are applied to the next commit. 

`git commit -m "commit message"` - This will commit your *staged* changes with a message of your choice.

`git push origin mynewbranch` - Push the commit(s) you made to the branch you are working on. Avoid pushing directly to master. 

When you have changes you want applied to master, open a pull request. Assign yourself as the "assignee" and "baumgartner-brody" as the reviewer. 
