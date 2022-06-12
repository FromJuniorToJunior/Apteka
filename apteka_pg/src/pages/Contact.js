import React from 'react'

function Contact() {
  return (
    <div className='contact'>
    <div className='container-fluid'> 
      <div className='row'>   
        <div className='col-md-6'>
              <div className='leftSide'>
                          <iframe 
                          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2395.74240094912!2d23.12215651582907!3d53.096861179927586!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x471ffbe4a18da57b%3A0xf4b4fbf211f40878!2zR29yxIVjeSBUcsOzamvEhXQ!5e0!3m2!1spl!2spl!4v1648747751146!5m2!1spl!2spl" 
                          width="700" 
                          height="550" 
                          style={{ border: 0 }} 
                          allowFullScreen="" 
                          loading="lazy" 
                          referrerPolicy="no-referrer-when-downgrade"/>

              </div>
        </div>     
        <div className='col-md-6'>
              <div className='rightSide'>
                  <div className='zadzwon_email_apteka_apteka center'>
                  <h3>Zadzwoń</h3>
                  +665314710
                  <h3>E-mail</h3>
                  kamilandronik1@wp.pl 
                  <h3>Apteka 1</h3>
                  ul. Mickiewicza 8, 33-170 Tuchów
                  <h3>Apteka 2</h3>
                  ul. Mickiewicza 8, 33-170 Tuchów
                  </div>
              </div>
              <div style={{'clear':'both'}}></div>
        </div>       
      </div>   
    </div>   
    </div>
  )
}

export default Contact
