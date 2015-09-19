import sys
import requests
import unittest
import argparse



class TestServer(unittest.TestCase):

	def __init__(self, testname, host, port):
		super(TestServer, self).__init__(testname)
		self.host = host
		self.port = port

	def test_unknown_url(self):
		url = "http://" + self.host + ":" + self.port + "/badurl"
		r = requests.get(url)
		self.assertEqual(r.status_code, 404)

	def test_unimplemented_method(self):
		url = "http://" + self.host + ":" + self.port 
		r = requests.post(url)
		self.assertEqual(r.status_code, 405)

	def test_rating_ok_httpstatus(self):
		url = "http://" + self.host + ":" + self.port + "/rating?businessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 200)

	def test_rating_ok_rating(self):
		url = "http://" + self.host + ":" + self.port + "/rating?businessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(body['rating'], 2.75)

	def test_rating_ok_success(self):
		url = "http://" + self.host + ":" + self.port + "/rating?businessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(body['success'], True)

	def test_rating_badparam(self):
		url = "http://" + self.host + ":" + self.port + "/rating?bsinessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 404)

	def test_rating_badid_httpstatus(self):
		url = "http://" + self.host + ":" + self.port + "/rating?businessID=4kx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 200)

	def test_rating_badid_success(self):
		url = "http://" + self.host + ":" + self.port + "/rating?businessID=4kx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(body['success'], False)

	def test_businesses_success(self):
		url = "http://" + self.host + ":" + self.port + "/businesses"
		r = requests.get(url)
		self.assertEqual(r.status_code, 200)

	def test_businesses_body(self):
		url = "http://" + self.host + ":" + self.port + "/businesses"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(len(body['businesses']), 13490)

	def test_reviewers_ok_httpstatus(self):
		url = "http://" + self.host + ":" + self.port + "/reviewers?businessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 200)

	def test_reviewers_ok_body(self):
		url = "http://" + self.host + ":" + self.port + "/reviewers?businessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(len(body['users']), 8)

	def test_reviewers_badparam(self):
		url = "http://" + self.host + ":" + self.port + "/reviewersbusinessID=4lkx2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 404)

	def test_reviewers_badid_httpstatus(self):
		url = "http://" + self.host + ":" + self.port + "/reviewers?businessID=4lk2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		self.assertEqual(r.status_code, 200)


	def test_reviewers_badid_success(self):
		url = "http://" + self.host + ":" + self.port + "/reviewers?businessID=4lk2UdISL1-2hfcTYI0lg"
		r = requests.get(url)
		body = r.json()
		self.assertEqual(body['success'], False)


args = None

if __name__ == "__main__":

	if(len(sys.argv) != 3):
		print "usage: python http_server_test.py <host> <port>"
		sys.exit()

	host = sys.argv[1]
	port = sys.argv[2]

	print "host: " + host + " port: " + port

	suite = unittest.TestSuite()
	suite.addTest(TestServer("test_unknown_url", host, port))
	suite.addTest(TestServer("test_unimplemented_method", host, port))
	suite.addTest(TestServer("test_rating_ok_httpstatus", host, port))
	suite.addTest(TestServer("test_rating_ok_rating", host, port))
	suite.addTest(TestServer("test_rating_ok_success", host, port))
	suite.addTest(TestServer("test_rating_badparam", host, port))
	suite.addTest(TestServer("test_rating_badid_httpstatus", host, port))
	suite.addTest(TestServer("test_rating_badid_success", host, port))
	suite.addTest(TestServer("test_businesses_success", host, port))
	suite.addTest(TestServer("test_businesses_body", host, port))
	suite.addTest(TestServer("test_reviewers_ok_httpstatus", host, port))
	suite.addTest(TestServer("test_reviewers_ok_body", host, port))
	suite.addTest(TestServer("test_reviewers_badparam", host, port))
	suite.addTest(TestServer("test_reviewers_badid_httpstatus", host, port))
	suite.addTest(TestServer("test_reviewers_badid_success", host, port))

	unittest.TextTestRunner().run(suite)
