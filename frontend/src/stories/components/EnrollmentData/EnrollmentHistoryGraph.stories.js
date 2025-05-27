import React from "react";

import EnrollmentHistoryGraphs from "main/components/EnrollmentData/EnrollmentHistoryGraph";

import {
  oneEnrollmentDataPoint,
  threeEnrollmentDataPoints,
} from "fixtures/enrollmentDataPointFixtures";

// Stryker disable all : placeholder for future implementation
export default {
  title: "components/EnrollmentData/EnrollmentDataTable",
  component: EnrollmentHistoryGraphs,
};

const Template = (args) => {
  return <EnrollmentHistoryGraphs {...args} />;
};

export const Empty = Template.bind({});
Empty.args = {
  enrollmentHistory: [],
};

export const OneEnrollmentData = Template.bind({});
OneEnrollmentData.args = {
  enrollmentHistory: oneEnrollmentDataPoint,
};

export const ThreeEnrollmentData = Template.bind({});
ThreeEnrollmentData.args = {
  enrollmentHistory: threeEnrollmentDataPoints,
};
