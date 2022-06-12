import React from 'react'
import Navbar from './components/Navbar'
import Home from './pages/Home'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Footer from './components/Footer'
import Header from './components/Header'
import Contact from './pages/Contact'
import Profile from './pages/Profile'
import LogIn from './pages/LogIn'
import Registration from './pages/Registration'
import History from './pages/History';


function App() {

  return (
    <div className="App">
         <Router>
              <Navbar />
              {/* <div>
              {isLoading ? (
                "Loading..."
              ) : (
                data.map((user, key) => (
                  <h1 key={key}>
                    {user.name} {user.price} 
                  </h1>
                ))
              )}
            </div> */}
              <Header />
              <Routes>
                    <Route path="/" exact element={<Home/>} />
                    <Route path="/contact" exact element={<Contact/>}  />
                    <Route path="/profile" exact element={<Profile/>}  />
                    <Route path="/history" exact element={<History/>}  />
                    <Route path="/logIn" exact element={<LogIn/>} />
                    <Route path="/registration" exact element={<Registration/>} />
                    
                  
              </Routes>
              <Footer />
         </Router>
    </div>
  );
}

export default App;
