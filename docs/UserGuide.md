---
layout: page
title: User Guide
---

------------------------------------------------------------------------------------------------

# User Guide for Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.
 
## Table of contents
 
 * Table of Contents
 {:toc}

--------------------------------------------------------------------------------------------------------------------

# 1 Quick start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `Taskmania.jar` from [here](https://github.com/AY2021S1-CS2103T-W10-3/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your TaskMania.

4. Double-click the file to start the app. The window that appears will be similar to the below should appear in a few seconds. Note how
 the app contains some sample information.<br>
   ![Ui](images/Ui.png)
   *Figure 1: A view of Taskmania at startup*

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
 open the help window.<br>
   Some commands you can try:

   * **`startproject 1`** : Opens the first project

   * **`exit`** : Exits the app

6. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* One and only one item in parenthesis should be supplied by the user
  e.g. `(ta/ASSIGNEE_GIHUB_USERNAME)||(td/DEADLINE)` can be used as "ta/Alice98" or "td/31-12-2020 10:00:00", but not as "ta/Alice98 td/31-12-2020 10:00:00" or "".

* Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]…​` can be used as ` ` (i.e. 0 times), `tg/friend`, `tg/friend tg/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

**:information_source: Notes about scoping:**<br>

The hierarchy of command scoping is as follows:
* global
    * `PROJECT_LIST`
      * `PROJECT`
        * `TASK`
        * `TEAMMATE`
    * `PERSON_LIST`
      * `PERSON`

<br>A lower-level scope always belongs to any parent scopes. For example, if the app is currently in `PROJECT`
scope, it is also in the `PROJECT_LIST` scope. However, it is not necessarily in `TASK` scope because `TASK` is
a child level of `PROJECT` and it is definitely not in `PERSON` scope because `PERSON` is parallel to `PROJECT`.

</div>

--------------------------------------------------------------------------------------------------------------------

# 2 Features

## 2.1 Features in global scope

### 2.1.1 Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)
   *Figure 2: Help message popup*

Format: `help`

### 2.1.2 Exit application : `exit`

Closes the application for the user.

Format: exit

### 2.1.3 List all projects in the catalogue `listprojects`

List all projects currently in the project catalogue.

Format: `listprojects`
- Lists all projects if there are projects in the catalogue

Example: `listprojects` lists all projects in the catalogue to the user.

### 2.1.4 List all persons in the catalogue `listpersons`

List all persons currently in the catalogue.

Format: `listpersons`
- Lists all persons if there are persons in the catalogue

Example: `listpersons` lists all persons in the catalogue to the user.

### 2.1.5 Leave a current page `leave`

Leave the current page and go back to the parent scope (one level up).

Format: `leave`
- Leaves the current page (clear the right-most non-empty dashboard) and shifts the scoping status to the parent scope
- If the app is already in the global, `PERSON_LIST`, or `PROJECT_LIST`, then the command takes no effect

# 2.2 Features in project list scope

### 2.2.1 Start work on an existing project `startproject`

Initialises the project specified.

Format: `startproject INDEX`
- Initialises the project at the specified INDEX
- The index refers to the index number shown in the displayed project list
- The index must be a positive integer 1, 2, 3, …​

Examples: `startproject 2` Initialises the second project in the project list.

### 2.2.2 Add a new project to the catalogue `add`

Adds a project to the project list.

Format: `add n/PROJECT_NAME dl/DEADLINE ru/REPO_URL d/PROJECT_DESCRIPTION [tg/TAGS]... `
  - The fields can be entered in any order, as long as the prefixes are matched correctly
  - Project Name can be any alphanumeric value (containing only alphabets and / or numbers)
  - Deadline follows the format *DD-MM-YYYY hh:mm:ss*
  - Deadline can be set to be in the past (in case the user wants to log finished projects for the completeness of project management)
  - Repo URL must be a valid link
  - Description can be anything, as long as it is not blank
  - Any number of tags can be added, where each new tag would require the prefix tg/ before the tag

Note: Please enter a valid repository URL. Taskmania is an offline application and can only check the validity of the
 URL, and not whether the repository exists.

Example: `add n/Blair project dl/29-02-2020 00:00:00 ru/http://github.com/a/b.git d/Coding in Greenwich tg/hell tg/abs` 

Adds a new project with the 
- projectName Blair project 
- deadline of 29 February 2020 midnight 
- URL for the team repository 
- Coding in Greenwich as the description 
- 2 tags "hell" and "abs"

### 2.2.3 Delete a project from the catalogue `delete`

Deletes a project and all associated information from the project catalogue.

Format: `delete INDEX `
- Deletes the project at the specified INDEX
- The index refers to the index number shown in the displayed project list
- The index must be a positive integer 1, 2, 3, …​

Examples: `delete 2` deletes the second project from the catalogue.

### 2.2.4 Locate projects by keyword `find`

Finds projects whose names contain the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]...`
- The search is case-insensitive. e.g `run` will match `Run`
- The order of the keywords does not matter. e.g. `Project CS2103T` will match `CS2103T Project`
- Only the name of the projects are searched
- Must provide at least one keyword
- There can be multiple keywords
- Keywords provided must be **complete words** and will only match **complete words** from the name of the project. e.g. `find Task` will not return the **Taskmania** project.

Example: `find scare` returns the **Scare House** and **Easily scare Night** projects.

Outcome: The projects with matching names will be shown to the user.

### 2.2.5 Edits details of a project `edit`

Updates the details of a project.

Format: `edit INDEX [n/PROJECT NAME] [dl/DEADLINE] [ru/REPO URL] [d/PROJECT DESCRIPTION] [tg/TAGS...] `
  - Edits the project at the specified index
  - The index refers to the index number shown in the displayed task list
  - Any combination of the fields above can be entered
  - The information entered will replace all the data in each respective field
  - Project Name can be any alphanumeric value (containing only alphabets and / or numbers)
  - Deadline follows the format *DD-MM-YYYY hh:mm:ss*
  - Deadline can be set to be in the past (in case the user wants to log finished projects for the completeness of project management)
  - Repo URL must be a valid link
  - Description can be anything, as long as it is not blank
  - Any number of tags can be added, separated by space " "

Note: Please enter a valid repository URL. Taskmania is an offline application and can only check the validity of the
 URL, and not whether the repository exists.

Example: `edit 1 n/Resident Evil project /d new horror` changes the name of the first project in the list to **Evil project**, and the description to **new horror**.

# 2.3 Task-related features in project scope

### 2.3.1 Add task to a project `addtask`

Creates a new task and adds it to the current project.

Format: `addtask tn/TASK NAME tp/TASK PROGRESS td/TASK DEADLINE `
  - All fields above are required
  - Task Name can be any alphanumeric value (containing only alphabets and / or numbers)
  - Task progress is a percentage value indicating how much of the task is done
  - Task deadline is indicated by a date and time with format *DD-MM-YYYY hh:mm:ss* 
  - Deadline can be set to be in the past (in case the user wants to log finished tasks for the completeness of project management)

Example: `addtask tn/Do User Guide tp/30 td/29-02-2020 00:00:00` creates a task named Do User Guide, 30% completed, and has a deadline of 29th Feb 2020, midnight.

### 2.3.2 Assign a task to a teammate `assign`

Assigns a task to a teammate within a project.

Format: `assign TASKINDEX TEAMMATE_GITHUB_USERNAME  `

  - TEAMMATE_GITHUB_USERNAME is the unique Github username of each teammate
  - Assigns the teammate with the given Github username to the task at the specified index
  - The index refers to the index number shown in the displayed task list

Example: `assign 3 Lucas98` assigns task number 3 in the list to user *Lucas98*.

### 2.3.3 Edit task to a project `edittask`

Edits the indicated task in a project.

Format: `edittask INDEX [n/TASK_NAME] [tp/TASK_PROGRESS] [td/TASK_DEADLINE] `
  - Edits the task at the specified index
  - The index refers to the index number shown in the displayed task list
  - Any combination and any number of the subsequent fields above can be entered
  - The information entered will replace all the data in each respective field
  - Task Name can be any alphanumeric value (containing only alphabets and / or numbers)
  - Task progress is a percentage value indicating how much of the task is done
  - Task deadline is indicated by a date and time with the format *DD-MM-YYYY hh:mm:ss* 
  - Deadline can be set to be in the past (in case the user wants to log finished tasks for the completeness of project management)

Example: `edittask 3 tn/Finish project` changes the name of task 3 in the list to Finish project.

### 2.3.4 Delete a task from the project `deletetask`

Deletes a task and all associated information from the project.

Format: `deletetask INDEX`

- Deletes the project at the specified `INDEX`
- The `INDEX` refers to the index number shown in the displayed task list
- The `INDEX` must be a positive integer 1, 2, 3, …​

Examples: `deletetask 2` deletes the second task shown in the displayed task list.

### 2.3.5 Filter tasks `filter`

Filters tasks in the task list by various task attributes:
  - by assignee's GitHub username - `ta/ASSIGNEE_GITHUB_USERNAME`
  - by task's name - `tn/KEYWORD [MORE_KEYWORDS]...`
  - by deadline (either specifying a deadline - `td/DEADLINE` or a time range for the deadline - `start/START_DATE end/END_DATE` )
  - by progress - `tp/TASK_PROGRESS`
  - by done status - `done/DONE_STATUS` (when a task's progress is 100, it is "done")

Format: `filter (ta/ASSIGNEE_GITHUB_USERNAME)||(tn/KEYWORD [MORE_KEYWORDS]...)||(td/DEADLINE)||(start/START_DATE end/END_DATE)||(tp/TASK_PROGRESS)||(done/DONE_STATUS)` 

- User may choose one task attribute to filter tasks by

Specifically:

1. `filter ta/ASSIGNEE_GITHUB_USERNAME` finds all the tasks that are assigned to the person with the given Github username.
2. `filter tn/KEYWORD [MORE_KEYWORDS]...` finds tasks whose names contain the given keywords
   - The search is case-insensitive. e.g `data` will match `Data`
   - The order of the keywords does not matter. e.g. `model refactoring` will match `refactoring model`
   - Only the name of the tasks are searched
   - Must provide at least one keyword
   - There can be multiple keywords
   - Keywords provided must be **complete words** and will only match **complete words** from the name of the task. e.g. `filter tn/dat` will not return the **Refine data flow** task

3. `filter td/DEADLINE` finds all the tasks whose deadlines match the given `DEADLINE`
   - Deadline of the task follows the format *DD-MM-YYYY hh:mm:ss*
4. `filter start/START_DATE end/END_DATE` finds all tasks whose deadlines are within the time range specified by the `START_DATE` and `END_DATE`
   - Start date and end date in the time range follows the format *DD-MM-YYYY*
   - There should be a space between `START_DATE` and `end/`. e.g.`filter start/01-11-2020end/02-11-2020` is invalid
5. `filter tp/TASK_PROGRESS` finds all tasks whose progress match the given `TASK_PROGRESS`
   - `TASK_PROGRESS` should only be integers between 0 and 100 inclusive, and it should not be blank
6. `filter done/true` finds all completed (progress is 100) tasks and `filter done/false` finds all unfinished tasks



Example: `filter tn/CS2103T` finds all the tasks whose task names contain the keyword `CS2103T`, and displays those tasks.

### 2.3.6 List all tasks `alltasks`

Lists all tasks in the task list of the project.

Format: `alltasks `

Example: `alltasks` displays all tasks in the task list.

### 2.3.7 Sort tasks `sort `

Sorts tasks in the task list by various task's attributes in ascending/descending order:

  - by deadline - `td/`
  - by progress - `tp/`
  - by task's name - `tn/`
  - by done status - `done/` (when a task's progress is 100, it is "done")

Format: `sort (sa/)||(sd/) (td/)||((tp/)||(tn/)||(done/)` 

  - User may choose the sorting order (`sa/` for ascending order and `sd/` for descending order)
  - User may choose one attribute of task to sort the task list

Example: `sort sa/ td/` sorts the task list by task deadline in ascending order. Then the tasks on the top of the task list are those with imminent deadlines.

### 2.3.8 View details of a task `viewtask`

View all the details of a task, beyond the little information given in the project view.

Format: `viewtask INDEX `
  - View all the information of the task specified by the INDEX
  - Index has to be a valid number that is in the range of tasks displayed on screen

Example: `viewtask 4` displays all information from task number 4 in the list.

# 2.4 Teammate-related features in project scope

### 2.4.1 Create a new teammate in a project `addteammate`

Creates a new teammate in a project with all the relevant fields contained in it.

Format: `addteammate mn/TEAMMATE_NAME mg/GITHUB_USERNAME mp/PHONE_NUMBER me/EMAIL ma/ADDRESS`
  - All fields are necessary to fill in
  - Teammate name has to be 1 or more words consisting only of letters
  - The Github username has to be a unique Github registered User Name
  - The phone number has to be a minimum of 3 and maximum of 16 numbers
  - The email has to have a proper prefix and proper domain name consisting of at least 2 letters
  - Address can be any amount of letters, symbols and numbers, the only constraint is that it cannot be blank

Example: `addteammate mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road` creates a new teamamte in the respective project with:
  - name Lucas
  - Github username of Lucas98
  - phone number of 93824823
  - email of lucas@gmail.com
  - address of 18 Evelyn road

### 2.4.2 Add a teammate to a project `addtoproject`

Add an existing teammate to the current project.

Format: `addtoproject GITHUB_USERNAME`

Example: `addtoproject Lucas98` adds Lucas98 to the current project that the user is in.

### 2.4.3 Remove a teammate from a project `deletefromproject`

Removes an existing teammate from the current project.

Format: `deletefromproject GITHUB_USERNAME`

Example: `deletefromproject Lucas98` removes Lucas98 from the current project.

### 2.4.4 Edit a teammate’s details `editteammate`

Update the information of a teammate.

Format: `editteammate GITHUB_USERNAME [mn/TEAMMATE_NAME] [mp/PHONE_NUMBER] [me/EMAIL] [ma/ADDRESS]`
  - Any combination or number of fields can be filled in
  - Teammate name has to be 1 or more words consisting only of letters
  - The Github User name cannot be changes, but is required to identify the teammate to edit
  - The phone number has to be a minimum of 3 and maximum of 16 numbers
  - The email has to have a proper prefix and proper domain name consisting of at least 2 letters
  - Address can be any amount of letters, symbols and numbers, the only constraint is that it cannot be blank

Example: `editteammate Lucas98 tn/GeNiaaz ta/5 Hacker Way` changes the name of the teammate to GeNiaaz and the address of said teammate to 5 Hacker Way.

### 2.4.5 View a teammate’s details `viewteammate`

View all of a specific teammate's details.

Format: `viewteammate GITHUB_USERNAME`

Example: `viewteammate Lucas98` displays all the information about the teammate with the Github User Name Lucas98 to the user.

### 2.4.6 Delete a teammate `deleteteammate`

Delete all of a specific teammate's details, as well as remove teammate from all projects teammate was a part of.

Format: `deleteteammate GITHUB_USERNAME`

Example: `deleteteammate Lucas97` deletes the teammate with Github username Lucas97, and removes him from any project he
 was in.

# 2.5 Features in person scope

### 2.5.1 Start work on an existing person `startperson`
Initialises the person specified.

Format: `startperson INDEX`
- Initialises the person at the specified INDEX
- The index refers to the index number shown in the displayed person list
- The index must be a positive integer 1, 2, 3, …​

Examples: `startperson 2` Initialises the second person in the person list.

# 3 FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Taskmania home folder.

# 4 Summary

Action | Format, Examples | Scope
--------|------------------|-------
**Get Help** | `help` | global scope
**Exit application** | `exit` | global scope
**Show all projects** | `listprojects` | global scope 
**Show all persons** | `listpersons` | global scope 
**Leave a view** | `leave` | global scope
**Start a project** | `startproject INDEX`<br> e.g., `startproject 3` | project_list scope
**Start a person** | `startperson INDEX`<br> e.g., `startperson 3` | person_list scope
**Add** | `add n/PROJECT_NAME dl/DEADLINE ru/REPO_URL d/PROJECT_DESCRIPTION [tg/TAGS]... `   eg, `add n/Blair project dl/29-02-2020 00:00:00 ru/http://github.com/a/b.git d/Coding in Greenwich tg/challenging` | global scope
**Delete project** | `delete INDEX` <br> e.g. `delete 2` | global scope
**Find KEYWORD** | `find KEYWORD` <br> e.g. `find read` | global scope
**edit** | `edit [n/PROJECT NAME] [dl/DEADLINE] [ru/REPO URL] [d/PROJECT DESCRIPTION] [tg/TAGS...] ` eg, `edit n/Resident Evil project /d new horror`| global scope
**Add Task** | `addtask n/TASK_NAME tp/TASK_PROGRESS td/TASK_DEADLINE ` eg, `addtask n/Do User Guide tp/30 td/29-02-2020 00:00:00` | project scope
**Assign A Task To A Teammate** | `assign INDEX NAME` <br> e.g. `assign 1 Niaaz` | project scope
**Edit task details** | `edittask INDEX [n/TASK_NAME[ [tp/TASK_PROGRESS] [td/TASK_DEADLINE] ` eg, `edittask 3 tn/Finish project status/true` | project scope
**Delete a task** | `deletetask INDEX` <br>e.g. `deletetask 1` | project scope 
**Filter tasks** | <code>filter (ta/ASSIGNEE_GITHUB_USERNAME)&#124;&#124;(tn/KEYWORD [MORE_KEYWORDS]...) (td/DEADLINE) (start/START_DATE end/END_DATE) (tp/TASK_PROGRESS) (done/DONE_STATUS)e.g. `filter tn/CS2103T` | project scope
**Show all the tasks** | `alltasks` | project scope 
**Sort tasks** | <code>sort (sa/)&#124;&#124;(sd/) (td/)&#124;&#124;(tp/)&#124;&#124;(tn/)&#124;&#124;(done/)</code> <br>e.g. `sort sa/ td/` | project scope 
**View Details of A Task** | `viewtask INDEX` <br> e.g. `viewtask 1` | project scope
**Create a new teammate** | `newteammate mn/TEAMMATE_NAME mg/GITHUB_USERNAME mp/PHONE_NUMBER me/EMAIL ma/ADDRESS` e.g. `newteammate mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road` | project scope
**Add a teammate to a project** | `addtoproject GITHUB_USERNAME` e.g. `addtoproject LucasTai98` | project scope
**Remove a teammate from a project** | `deletefromproject GITHUB_USERNAME` e.g. `deletefromproject LucasTai98` | project
 scope
**Edit teammate details** | `editteammate GITHUB_USERNAME [mn/TEAMMATE_NAME] [mp/PHONE_NUMBER] [me/EMAIL] [ma/ADDRESS]` e.g. `editteammate Lucas98 tn/GeNiaaz ta/5 Hacker Way`|
**View a teammate’s details** | `viewteammate GITHUB_USERNAME` e.g. `viewteammate Lucas98`| project scope
**Delete a teammate** | `deleteteammate GITHUB_USERNAME` e.g. `deleteteammate Lucas98` | project scope

# 5 Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Teammate**: A person belonging to a project of the team leader's team.
* **Participation**: The class of an object that handles the relations between a Project object and Person Object.
* **Scope**: The confines of when certain commands will work.
