<template>
  <div class="meal-plan">
    <table class="table">
      <caption>
        <input
          type="text"
          id="mealPlanName"
          name="mealPlanName"
          placeholder="Enter name for meal plan"
        />
      </caption>
      <thead>
        <tr>
          <th>Monday</th>
          <th>Tuesday</th>
          <th>Wednesday</th>
          <th>Thursday</th>
          <th>Friday</th>
          <th>Saturday</th>
          <th>Sunday</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
          <td><button>Add A Meal</button></td>
        </tr>
      </tbody>
    </table>
    <br>
    <button>Add Ingredients To Grocery List</button>
    <button>Clear Meal Plan</button>
  </div>
</template>

<script>
export default {};
</script>

<style>
.table{
  margin-left: auto;
  margin-right: auto;
}
</style>