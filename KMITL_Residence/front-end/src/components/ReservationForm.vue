<template>
<form class="ui form">
    <div class="fields">
        <div class="ui large input four wide field">
            <label>Date Check-in</label>
            <input type="date">
        </div>

        <div class="ui large input four wide field">
            <label>Date Check-out</label>
            <input type="date">
        </div>

        <div class="ui large input two wide field">
            <label>Adults</label>
            <select class="ui dropdown">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
        </div>

        <div class="ui large input two wide field">
            <label>Children</label>
            <select class="ui dropdown">
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
        </div>

        <div class="ui large input four wide field">
            <label>​&nbsp;</label>
            <button class="ui button fluid luxury-primary">
                Book Room
            </button>
        </div>
    </div>
</form>
</template>

<script>
export default {

}
</script>

<style scoped>
div.input {
  display: block;
}

div.large.input.field > select, div.large.input.field > button{
  height: 43.11px;
}
</style>