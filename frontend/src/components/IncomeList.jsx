import { deleteIncome } from "../api/income";

const IncomeList = ({ incomes, refresh }) => {
  return (
    <ul>
      {incomes.map(i => (
        <li key={i.id}>
          {i.description} - ₹{i.amount}
          <button onClick={async () => {
            await deleteIncome(i.id);
            refresh();
          }}>
            ❌
          </button>
        </li>
      ))}
    </ul>
  );
};

export default IncomeList;
