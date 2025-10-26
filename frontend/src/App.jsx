import HomePage from "./pages/HomePage"

import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
  colorSchemes: {
    dark: false,
  }
})

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <HomePage />
    </ThemeProvider>
  )
}

export default App