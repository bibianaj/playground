import React, {} from 'react';
import { Route, Switch, withRouter } from 'react-router-dom';
// Redux
import compose from 'recompose/compose';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';

// Pages
import Header from './components/header/Header';

const mapStateToProps = state => {
    return {
  
    }
  }

  const Routes = props => {
      
    return (
        <div>
            <Switch>
                <Route path="/" render={props => <Header {...props}/>} />
            </Switch>
           
        </div>
    );
  }


export default compose(connect(mapStateToProps, {
    withRouter
}), withStyles())(Routes);