import axios from "axios";
class StaffService {
  async getAll() {
    return await axios.get("http://localhost:8080/staff");
  }
  async add(staff) {
    return await axios.post("http://localhost:8080/staff", staff);
  }

  async update(id, staff) {
    return await axios.put(`http://localhost:8080/staff/${id}`, staff);
  }

  async remove(id) {
    return await axios.delete(`http://localhost:8080/staff/${id}`);
  }
}

export default new StaffService();
