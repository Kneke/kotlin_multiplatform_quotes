import React from 'react';
import { render } from '@testing-library/react';
import QuotePage from './QuotePage';

test('renders learn react link', () => {
  const { getByText } = render(<QuotePage />);
  const linkElement = getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
