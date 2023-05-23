import axios from "axios";

function createHttpInstance() {
  let token = sessionStorage.getItem("Authorization");
  return axios.create({
    baseURL: "http://localhost:9999/",
    headers: {
      'Content-type': 'application/json',
      'Authorization': token
    },
  });
}

const http = createHttpInstance();

export default http;
