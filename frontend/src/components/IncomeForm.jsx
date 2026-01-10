import { useState } from "react";
import { addIncome } from "../api/income";
import { getUser } from "../utils/auth";

const IncomeForm = ({ refresh }) => {
  const user = getUser();

  const [form, setForm] = useState({
    description: "",
    source: "SALARY",
    amount: "",
    date: "",
  });

  const submit = async (e) => {
    e.preventDefault();
    await addIncome({ ...form, user });
    refresh();
  };

  return (
    <form onSubmit={submit}>
      <input placeholder="Description" onChange={e => setForm({...form, description: e.target.value})} />
      <select onChange={e => setForm({...form, source: e.target.value})}>
        <option value="SALARY">Salary</option>
        <option value="INVESTMENT">Investment</option>
        <option value="TRADING">Trading</option>
      </select>
      <input type="number" placeholder="Amount" onChange={e => setForm({...form, amount: e.target.value})} />
      <input type="date" onChange={e => setForm({...form, date: e.target.value})} />
      <button>Add Income</button>
    </form>
  );
};

export default IncomeForm;
