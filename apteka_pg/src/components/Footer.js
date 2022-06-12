import React from 'react'
import InstagramIcon from '@material-ui/icons/Instagram';
import TwitterIcon from '@material-ui/icons/Twitter';
import FacebookIcon from '@material-ui/icons/Facebook';
import LinkedInIcon from '@material-ui/icons/LinkedIn';

function Footer() {
  return (
    <div className='footer'>
      <footer className="py-5 bg-dark">
            <div className="container"><p className="m-0 text-center text-white">
              <TwitterIcon className='icon' style={{ color: '#4267B2', fontSize: '35px'}} />
              <InstagramIcon className='icon'   style={{ color: '#4267B2', fontSize: '35px'}}  />
              <FacebookIcon className='icon' style={{ color: '#4267B2', fontSize: '35px'}} />
              <LinkedInIcon className='icon'  style={{ color: '#4267B2', fontSize: '35px'}} />
              
              
              </p></div>
        </footer>
    </div>
  )
}

export default Footer
