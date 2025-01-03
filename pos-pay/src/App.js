import logo from "./logo.svg";
import "./App.css";
import QuickResponseCode from "./QuickResponseCode";
import { ApolloProvider, ApolloClient, InMemoryCache } from "@apollo/client";

const client = new ApolloClient({
  uri: "http://localhost:8080/graphql",
  cache: new InMemoryCache(),
});

function App() {
  return (
    <ApolloProvider client={client}>
      <div className="QrFrame">
        <header className="QrFrame-header">
          <img src={logo} className="QrFrame-logo" alt="logo" />
          <QuickResponseCode />
        </header>
      </div>
    </ApolloProvider>
  );
}

export default App;
