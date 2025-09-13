import axios from "axios";

export async function checkLogin(loginInfor) {
    try{
        const response = await axios.get("http://localhost:8080/accounts");
        const account = response.data.find(ac => ac.username == loginInfor.username && ac.password == loginInfor.password);
        if (account!=null){
            return account;
        }else {
            return null
        }
    }catch (e) {
        return null;
    }

}

export async function getAllAccounts() {
    return await axios.get("http://localhost:8080/accounts");
}