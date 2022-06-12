import React from 'react'
import '../styles/styles.css'

function Header() {
  return (
    <div className='header'>
        <header className="bg-dark py-5">
            <div className="container px-4 px-lg-5 my-5">
                <div className="text-center text-white">
                    <h1 className="display-4 fw-bolder">Kiedy serce jest spokojne, i ciało jest zdrowe.</h1>
                    <p className="lead fw-normal text-white-50 mb-0">~Hipokrates (466-370)</p>
                </div>
            </div>
        </header>
    </div>
  )
}

export default Header
