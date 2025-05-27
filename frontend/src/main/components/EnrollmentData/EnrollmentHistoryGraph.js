import React from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  Legend,
} from "recharts";

// Stryker disable all: This is not to be run until the tests are written as it is a placeholder.

export const formatTooltip = (value, _, props) => {
  return [
    `Enrollment: ${value.toFixed(1)}, Date Created: ${props.payload.dateCreated}`,
  ];
};

export const createCompleteEnrollmentData = (data) => {
  var data_list = [];

  for (let index = 0; index < data.length; index++) {
    const element = data[index];
    data_list.push(element.enrollment, element.dateCreated);
  }

  return data_list;
};

// Component to render a single bar chart for a specific group of data
const EnrollmentHistoryLineChart = ({ _data, title }) => {
  const completeData = [
    { enrollment: 50, dateCreated: "2020-05-14T17:50:52.360000" },
    { enrollment: 24, dateCreated: "2025-05-14T17:50:52.361636" },
    { enrollment: 125, dateCreated: "2025-05-14T17:50:52.356611" },
  ];

  return (
    <div data-testid="enrollment-history-graph">
      <h3>{title}</h3>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart
          data={completeData}
          margin={{ top: 5, right: 30, left: 60, bottom: 5 }}
        >
          <XAxis dataKey="dateCreated" />
          <YAxis
            tickFormatter={(value) => `${value.toFixed(1)}`}
            // Stryker restore all
          />

          <Legend />
          <Tooltip formatter={formatTooltip} />
          <Line dataKey="enrollment" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

const EnrollmentHistoryGraphs = ({ _enrollmentHistory }) => {
  const enrollmentHistory = [
    {
      data: [1, 2, 3],
    },
  ];

  return (
    <div data-testid="enrollment-history-graphs">
      <EnrollmentHistoryLineChart
        data={enrollmentHistory}
        title={"Title to be implemented"}
      />
    </div>
  );
};

export default EnrollmentHistoryGraphs;
