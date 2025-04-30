# 🏋️️ Workout Plan Generator Microservice

This microservice is responsible for generating **personalized weekly workout plans** based on user inputs such as age, training experience, weekly availability, and goal (e.g. fat loss or muscle gain).  
It selects exercises from Firebase, builds a smart split based on predefined templates, and returns a structured training plan.

---

## 🚀 Features

- 🔍 Dynamic workout generation based on:
  - User goal (`LOSE_WEIGHT`, `MUSCLE_GAIN`, `STRENGTH`)
  - Weekly training frequency (2–5 days)
  - Experience level (beginner, intermediate, advanced)

- 🧠 Smart logic:
  - Avoids duplicate exercises per week
  - Picks exercise per muscle group intelligently
  - Applies custom rep/set/rest schemes based on goal and muscle group

- 🔥 Clean code architecture:
  - Separation of concerns (Controller → Service → Generator)
  - Firebase integration for dynamic exercise data
  - Strategy pattern for exercise picking & rep schemes

---

## 📦 Technologies Used

- Java 17+
- Spring Boot
- Firebase Realtime DB
- Lombok
- SLF4J Logging

---

## 📤 API Endpoint

### `POST /api/workout-plan/generate`

Generate a full workout plan.

#### 🔸 Request Body (`WorkoutRequestDTO`)
```json
{
  "age": 25,
  "yearsOfExperience": 2.5,
  "daysPerWeek": 4,
  "goal": "LOSE_WEIGHT"
}
```

#### 🔹 Response (`WorkoutPlanEntity`)
```json
{
  "goal": "LOSE_WEIGHT",
  "days": [
    {
      "dayNumber": 0,
      "exercises": [
        {
          "name": "Push Up",
          "muscleGroup": "CHEST",
          "imageUrl": "...",
          "sets": 3,
          "reps": 15,
          "rest": 30
        }
      ]
    },
    ...
  ]
}
```

---

## 🧑‍💻 Author

Created with ❤️ by Gal Angel  
Feel free to open issues or contribute!
