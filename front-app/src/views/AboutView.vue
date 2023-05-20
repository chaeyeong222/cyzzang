<template>
  <div class="about">
    <h1>This is an about page</h1>
    
  </div>
</template>

<script>
export default {
  name: "aboutView",
  created() {
    document.body.classList.remove("loading");
  },
  methods: {
    showModal() {
      this.$refs["my-modal"].show();
    },
    hideModal() {
      this.$refs["my-modal"].hide();
    },
    toggleModal() {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs["my-modal"].toggle("#toggle-btn");
    },
  },
  data() {
    return {
      modalShow: false,
    };
  },
};
</script>
