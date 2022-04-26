<script>
import { Bar } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  components: { Bar },
  mounted() {
    this.$store.dispatch("results");
  },
  methods: {
    diceRoll() {
      this.$store.dispatch("diceRoll");
    },
  },
};
</script>

<template>
  <div class="hero">
    <div>
      <button @click="diceRoll()">Roll dice</button>
    </div>
    <div class="bar" v-if="this.$store.getters.dataLoaded">
      <Bar :chart-data="this.$store.getters.getChartData" />
    </div>
    <div v-else>
      <p>No data</p>
    </div>
  </div>
</template>

<style src="./DiceRollChart.css" lang="css"></style>