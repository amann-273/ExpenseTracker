import { deleteExpense } from "../api/expense";

const ExpenseList = ({ expenses, refresh }) => {
  return (
    <ul>
      {expenses.map(e => (
        <li key={e.id}>
          {e.description} - ₹{e.amount}
          <button onClick={async () => {
            await deleteExpense(e.id);
            refresh();
          }}>
            ❌
          </button>
        </li>
      ))}
    </ul>
  );
};

export default ExpenseList;
