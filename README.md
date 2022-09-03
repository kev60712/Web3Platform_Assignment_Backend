# Web3Platform_Assignment_Backend

## The Exercise I Choose and Why (Backend):
- I'm currently a software engineer at TSMC. Although I have experience in frontend and backend development, most of my job is related to backend development. I'm more confident in the backend, and I think I can finish the assignment faster. This is why I choose the backend assignment.

## Explanation and Screenshots:
#### 1. List compensation data via API GET request (a. Filter by one or more fields/attributes. b. Sort by one or more fields/attributes)
The dataset I choose is "salary_survey-1.json". After I go through the dataset, I found that some attributes have specific options for the user to choose from, such as "How old are you?", "What industry do you work in" and so on. Therefore, for the first user requirement, I only let users filter the data by age, industry, currency, location and workExperience. Because I think letting users able to filter any columns they want doesn't make sense and may affect query performance. As for sort by columns, user can choose whatever columns they want to sort the data. **All the query parameters are optional, the function can based on different input to adjust the query sql.**

![截圖 2022-09-03 下午6 02 01](https://user-images.githubusercontent.com/20982158/188265745-85ca3460-ed70-483e-931f-8d07c926721f.png)
![截圖 2022-09-03 下午6 01 19](https://user-images.githubusercontent.com/20982158/188265736-fa7c8c39-7c23-4283-9ee7-5211e1ab8dd0.png)

#### 2.  Fetch a single record via GET request (e.g. `/compensation_data?fields=first_name,last_name,salary`)
In the dataset I found, I didn't find the column for first_name, last_name, and salary. So I didn't run the function in my demo. But I do build a function for it. I think as long as the first_name, last_name, and salary can find a single record, my function is able used successfully. But I think for this requirement, we should add more query conditions like "id" because I think there will be some edge cases if we query records based on only name and salary.

![截圖 2022-09-03 下午6 35 45](https://user-images.githubusercontent.com/20982158/188266716-a615a2e0-3305-401e-8706-d6381dfcb2a1.png)


#### 3. Have the JSON response be normalized into a uniform schema via a serializer or json template
In this requirement, I convert my object into JSON String and return it. But I think the response content needs to have more discussion.

![截圖 2022-09-03 下午5 50 20](https://user-images.githubusercontent.com/20982158/188265422-8d9913b3-539e-4d96-a14f-52853d611f1e.png)
![截圖 2022-09-03 下午5 56 12](https://user-images.githubusercontent.com/20982158/188265576-73b712df-419a-4614-9b42-f8ddce0654bd.png)

## How to test/demo/run
The language and framework I used is java and spring boot. I don't think the application is runnable in your side, because the applicaiton need to connect with the mysql database, so it may needs to install mysql and create the table (CompensationRecord). But I do write some test function, which is able to run to prove the function is working.

![截圖 2022-09-03 下午6 58 35](https://user-images.githubusercontent.com/20982158/188267384-128a6cb5-aff0-404b-ae95-0bf0d7fca229.png)

## Feedback:
The assignment is interesting. But I had a tight deadline at my job this week, so I can only do this assignment at the last moment. 
I think some requirements can have more explanation. When I programmed the logic, I think I need to clarify some points with the user, such as requirement 2, but sadly I have no time for this. Maybe this is the assignment want. In conclusion, I like this assignment, and it is a pretty nice practice.

