import { useState } from "react";
import { addExpense } from "../api/expense";
import { getUser } from "../utils/auth";

const ExpenseForm = ({ refresh }) => {
  const user = getUser();

  const [form, setForm] = useState({
    description: "",
    category: "PERSONAL",
    amount: "",
    date: "",
  });

  const submit = async (e) => {
    e.preventDefault();
    await addExpense({ ...form, user });
    refresh();
  };

  return (
    <form onSubmit={submit}>
      <input placeholder="Description" onChange={e => setForm({...form, description: e.target.value})} />
      <select onChange={e => setForm({...form, category: e.target.value})}>
        <option value="PERSONAL">Personal</option>
        <option value="SURVIVAL">Survival</option>
        <option value="INVESTMENT">Investment</option>
      </select>
      <input type="number" placeholder="Amount" onChange={e => setForm({...form, amount: e.target.value})} />
      <input type="date" onChange={e => setForm({...form, date: e.target.value})} />
      <button>Add Expense</button>
    </form>
  );
};

export default ExpenseForm;
