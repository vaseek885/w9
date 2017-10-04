import { Lab91Page } from './app.po';

describe('lab91 App', () => {
  let page: Lab91Page;

  beforeEach(() => {
    page = new Lab91Page();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
